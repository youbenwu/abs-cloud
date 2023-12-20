package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.AdvertBuyOrderDao;
import com.outmao.ebs.portal.domain.AdvertBuyOrderDomain;
import com.outmao.ebs.portal.dto.AdvertBuyOrderDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class AdvertBuyOrderDomainImpl extends BaseDomain implements AdvertBuyOrderDomain {


    @Autowired
    private AdvertBuyOrderDao advertBuyOrderDao;

    @Transactional
    @Override
    public AdvertBuyOrder saveAdvertBuyOrder(AdvertBuyOrderDTO request) {
        AdvertBuyOrder order=new AdvertBuyOrder();
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,order);
        advertBuyOrderDao.save(order);
        return order;
    }

    @Transactional
    @Override
    public AdvertBuyOrder setAdvertBuyOrderStatus(SetAdvertBuyOrderStatusDTO request) {
        AdvertBuyOrder order=advertBuyOrderDao.findByOrderNoLock(request.getOrderNo());
        order.setStatus(request.getStatus());
        advertBuyOrderDao.save(order);
        return order;
    }



}
