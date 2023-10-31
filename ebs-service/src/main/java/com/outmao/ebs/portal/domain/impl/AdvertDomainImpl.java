package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.mall.order.common.constant.OrderStatus;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.dao.AdvertOrderDao;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertOrder;
import com.outmao.ebs.portal.entity.QAdvert;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class AdvertDomainImpl extends BaseDomain implements AdvertDomain {


    @Autowired
    private AdvertDao advertDao;

    @Autowired
    private AdvertOrderDao advertOrderDao;


    @Transactional
    @Override
    public Advert saveAdvert(AdvertDTO request) {
        Advert advert=request.getId()==null?null:advertDao.findByIdForUpdate(request.getId());


        if(advert==null){
            advert=new Advert();
            advert.setUserId(request.getUserId());
            advert.setOrgId(request.getOrgId());
            advert.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,advert,"orgId","userId");
        advert.setUpdateTime(new Date());

        advertDao.save(advert);

        return advert;
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
    public Advert buyPv(Long id,long buyPv,double buyAmount) {
        Advert advert=advertDao.findByIdForUpdate(id);
        advert.setBuyPv(buyPv+advert.getBuyPv());
        advert.setBuyAmount(buyAmount+advert.getBuyAmount());
        if(advert.getBuyPv()>advert.getPv()){
            //直接上架
            advert.setStatus(1);
        }
        advertDao.save(advert);
        return advert;
    }

    @Override
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        QAdvert e=QAdvert.advert;

        Predicate p=null;

        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.title.like("%"+request.getKeyword()+"%");
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
//        else{
//            Date now =new Date();
//            //前端只返回上架的，并且在显示时间之内的
//            p=e.status.eq(1).and(e.startTime.before(now).and(e.endTime.after(now))).and(p);
//        }

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
