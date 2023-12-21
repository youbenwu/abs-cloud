package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.dao.AdvertPlaceDao;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.domain.conver.AdvertVOConver;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.*;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.portal.vo.StatsAdvertStatusVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AdvertDomainImpl extends BaseDomain implements AdvertDomain {


    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private AdvertPlaceDao advertPlaceDao;


    private AdvertVOConver advertVOConver=new AdvertVOConver();

    @Transactional
    @Override
    public Advert saveAdvert(AdvertDTO request) {

        if(request.getStartTime()==null){
            request.setStartTime(new Date());
        }
        if(request.getEndTime()==null){
            request.setEndTime(DateUtil.addDays(request.getStartTime(),3600));
        }

        Advert advert=request.getId()==null?null:advertDao.findByIdForUpdate(request.getId());

        if(advert==null){
            advert=new Advert();
            advert.setUserId(request.getUserId());
            advert.setOrgId(request.getOrgId());
            advert.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,advert,"orgId","userId");

        if(StringUtils.isEmpty(advert.getImage())&&!StringUtils.isEmpty(advert.getImages())){
            String[] images=advert.getImages().split(",");
            advert.setImage(images[0]);
        }

        advert.setUpdateTime(new Date());

//        //修改信息要重新审核
//        advert.setStatus(Status.NotAudit.getStatus());
//        advert.setDisplay(false);

        advertDao.save(advert);

        if(request.getPlaces()!=null){
            //广告投放场所
            saveAdvertPlaceList(advert,request.getPlaces());
        }

        return advert;
    }

    private void saveAdvertPlaceList(Advert advert,String places){
        List<AdvertPlace> list=advertPlaceDao.findByAdvertId(advert.getId());
        Map<Long,AdvertPlace> listMap=list.stream().collect(Collectors.toMap(t->t.getPlaceId(),t->t));
        String[] ids=places.split(",");
        List<AdvertPlace> news=new ArrayList<>(ids.length);
        for(int i=0;i<ids.length;i++){
            String id=ids[i];
            if(id.length()>0){
                Long placeId=Long.parseLong(id);
                AdvertPlace place=listMap.get(placeId);
                if(place==null){
                    place=new AdvertPlace();
                    place.setAdvertId(advert.getId());
                    place.setPlaceId(placeId);
                }
                news.add(place);
            }
        }
        advertPlaceDao.saveAll(news);
        advertPlaceDao.deleteAllByAdvertIdAndIdNotIn(advert.getId(),news.stream().map(t->t.getId()).collect(Collectors.toList()));
        advert.setPlace(news.size()>0);
    }

    @Transactional
    @Override
    public Advert setAdvertDisplay(SetAdvertDisplayDTO request) {
        Advert advert=advertDao.findByIdForUpdate(request.getId());
        if(advert.getStatus()!= Status.NORMAL.getStatus()){
            throw new BusinessException("广告状态异常");
        }
        advert.setDisplay(request.isDisplay());
        advertDao.save(advert);
        return advert;
    }

    @Transactional
    @Override
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        Advert advert=advertDao.findByIdForUpdate(request.getId());
        if(advert==null)
            return null;
        advert.setStatus(request.getStatus());
        if(advert.getStatus()!=Status.NORMAL.getStatus()){
            advert.setDisplay(false);
        }else{
            advert.setDisplay(true);
        }
        advertDao.save(advert);
        return advert;
    }

    @Transactional
    @Override
    public void deleteAdvertById(Long id) {
        advertDao.deleteById(id);
    }



    @Override
    public void randomAdvertSort() {
        List<Advert> list=advertDao.findAll();

        Collections.shuffle(list);

        for (int i=0;i<list.size();i++){
            Advert advert=list.get(i);
            advertDao.setSort(advert.getId(),i);
        }

    }


    @Transactional
    @Override
    public Advert buy(Long id, AdvertBuy buy) {
        Advert advert=advertDao.findByIdForUpdate(id);
        if(advert==null){
            throw new BusinessException("广告不存在");
        }
        advert.setBuy(buy);
        advertDao.save(advert);
        return advert;
    }

    @Transactional
    @Override
    public Advert buyDisplay(Long id, AdvertBuyDisplay buyDisplay) {
        Advert advert=advertDao.findByIdForUpdate(id);
        if(advert==null){
            throw new BusinessException("广告不存在");
        }
        advert.setBuyDisplay(buyDisplay);
        advert.setStartTime(buyDisplay.getStartTime());
        advert.setEndTime(buyDisplay.getEndTime());
        advertDao.save(advert);
        return advert;
    }

    @Override
    public AdvertVO getAdvertVOById(Long id) {
        QAdvert e=QAdvert.advert;
        AdvertVO vo=queryOne(e,e.id.eq(id),advertVOConver);
        if(vo!=null) {
            setChannelTitle(vo);
        }
        return vo;
    }


    private void setChannelTitle(AdvertVO vo){
        QAdvertChannel c = QAdvertChannel.advertChannel;
        String channel = QF.select(c.title).from(c).where(c.id.eq(vo.getChannelId())).fetchOne();
        vo.setChannelTitle(channel);
    }

    private void setChannelTitle(List<AdvertVO> list){
        QAdvertChannel c = QAdvertChannel.advertChannel;
        List<Tuple> tuples = QF.select(c.title,c.id).from(c).where(c.id.in(list.stream().map(t->t.getChannelId()).collect(Collectors.toList()))).fetch();
        Map<Long,String> map=new HashMap<>();
        tuples.forEach(t->{
            map.put(t.get(c.id),t.get(c.title));
        });
        list.forEach(t->{
            t.setChannelTitle(map.get(t.getChannelId()));
        });

    }

    @Override
    public List<AdvertVO> getAdvertVOList(GetAdvertListDTO request) {

        QAdvert e=QAdvert.advert;

        Predicate p=getPredicate(request);

        List<AdvertVO> list=queryList(e,p,advertVOConver);

        setChannelTitle(list);

        return list;
    }


    @Override
    public List<AdvertVO> getAdvertVOListByIdIn(Collection<Long> idIn) {
        QAdvert e=QAdvert.advert;

        List<AdvertVO> list=queryList(e,e.id.in(idIn),advertVOConver);

        setChannelTitle(list);

        return list;
    }

    @Override
    public long getAdvertCount(GetAdvertListDTO request) {
        Predicate p=getPredicate(request);
        return advertDao.count(p);
    }

    @Override
    public Page<AdvertVO> getAdvertVOPage(GetAdvertListDTO request, Pageable pageable) {
        QAdvert e=QAdvert.advert;

        Predicate p=getPredicate(request);

        Page<AdvertVO> page=queryPage(e,p,advertVOConver,pageable);

        setChannelTitle(page.getContent());

        return page;
    }


    private Predicate getPredicate(GetAdvertListDTO request){
        QAdvert e=QAdvert.advert;

        Predicate p=null;

        if(request.getPlaceId()!=null){
            List<Long> idIn=advertPlaceDao.findAllAdvertIdByPlaceId(request.getPlaceId());
            p=e.id.in(idIn).or(e.isPlace.isFalse());
        }

        if(StringUtil.isNotEmpty(request.getCity())){
            p=e.citys.like("%"+request.getCity()+"%").or(e.citys.isEmpty()).or(e.citys.isNull()).and(p);
        }

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.title.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId()).and(p);
        }

        if(request.getChannelId()!=null){
            p=e.channelId.eq(request.getChannelId()).and(p);
        }

        if(request.getUserId()!=null){
            p=e.userId.eq(request.getUserId()).and(p);
        }else {
            p=e.status.lt(Status.ORDER_WAIT.getStatus()).and(p);
        }

        if(request.getSee()!=null){
            Date now =new Date();
            if(request.getSee()){
                //前端只返回在显示时间之内的
                p=e.startTime.before(now).and(e.endTime.after(now)).and(p);
            }
        }

        if(request.getDisplay()!=null){
            p=e.display.eq(request.getDisplay()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        return p;
    }


    @Override
    public List<StatsAdvertStatusVO> getStatsAdvertStatusVOList(GetAdvertListDTO request) {
        QAdvert e=QAdvert.advert;
        request.setStatus(null);
        Predicate p=getPredicate(request);
        List<Tuple> tuples= QF.select(e.count(),e.status).from(e).where(p).groupBy(e.status).fetch();
        List<StatsAdvertStatusVO> list=new ArrayList<>(tuples.size());
        for (Tuple t:tuples){
            StatsAdvertStatusVO vo=new StatsAdvertStatusVO();
            vo.setStatus(t.get(e.status));
            vo.setCount(t.get(e.count()));
            list.add(vo);
        }
        return list;
    }

    @Transactional
    @Override
    public void checkAdvertExpire() {
        Date time=DateUtil.addDays(new Date(),-1);
        List<Advert> list=advertDao.findAllByBuyDisplayExpireLock(time);
        list.forEach(a->{
            a.setStatus(Status.EXPIRE.getStatus());
        });
        advertDao.saveAll(list);
    }



}
