package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.dao.AdvertOrderDao;
import com.outmao.ebs.portal.dao.AdvertPlaceDao;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertOrder;
import com.outmao.ebs.portal.entity.AdvertPlace;
import com.outmao.ebs.portal.entity.QAdvert;
import com.outmao.ebs.security.util.SecurityUtil;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class AdvertDomainImpl extends BaseDomain implements AdvertDomain {


    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private AdvertPlaceDao advertPlaceDao;

    @Autowired
    private AdvertOrderDao advertOrderDao;


    @Transactional
    @Override
    public Advert saveAdvert(AdvertDTO request) {

        if(request.getStartTime()==null){
            request.setStartTime(new Date());
        }
        if(request.getEndTime()==null){
            request.setEndTime(DateUtil.addDays(request.getStartTime(),360));
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
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        Advert advert=advertDao.findByIdForUpdate(request.getId());
        if(advert.getStatus()==2){
            throw new BusinessException("需缴费");
        }
        BeanUtils.copyProperties(request,advert);
        advert.setUpdateTime(new Date());
        advertDao.save(advert);
        return advert;
    }

    @Transactional
    @Override
    public void deleteAdvertById(Long id) {
        advertDao.deleteById(id);
    }


    @Transactional
    @Override
    public void setAdvertSort(Long id, int sort) {
        advertDao.setSort(id,sort);
    }

    @Transactional
    @Override
    public Advert buyPv(Long id,long buyPv,double buyAmount) {
        Advert advert=advertDao.findByIdForUpdate(id);
        advert.setBuyPv(buyPv+advert.getBuyPv());
        advert.setBuyAmount(buyAmount+advert.getBuyAmount());
        advert.setBuyPrice(advert.getBuyAmount()/advert.getBuyPv());
        if(advert.getBuyPv()>advert.getPv()){
            //直接上架
            advert.setStatus(1);
        }
        advertDao.save(advert);
        return advert;
    }





    @Override
    public List<Advert> getAdvertList() {
        return advertDao.findAll();
    }

    @Override
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
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

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }


        if(!SecurityUtil.isAdminApi()){
            //Date now =new Date();
            //前端只返回在显示时间之内的
            //p=e.startTime.before(now).and(e.endTime.after(now)).and(p);
        }

        Page<Advert> page=p==null?advertDao.findAll(pageable):advertDao.findAll(p,pageable);

        return page;
    }

    @Transactional
    @Override
    public AdvertOrder saveAdvertOrder(AdvertOrder request) {
        if(request.getCreateTime()==null){
            request.setCreateTime(new Date());
        }
        AdvertOrder advertOrder= advertOrderDao.save(request);
        return advertOrder;
    }

    @Transactional
    @Override
    public AdvertOrder setAdvertOrderStatus(SetAdvertOrderStatusDTO request) {
        AdvertOrder advertOrder=advertOrderDao.findByOrderNo(request.getOrderNo());
        if(request.getStatus()==advertOrder.getStatus())
            return advertOrder;
        if(request.getStatus()== OrderStatus.SUCCESSED.getStatus()){
            if(advertOrder.getStatus()!=OrderStatus.WAIT_PAY.getStatus()){
                throw new BusinessException("状态异常");
            }
        }
        advertOrder.setStatus(request.getStatus());
        advertOrderDao.save(advertOrder);
        return advertOrder;
    }



}
