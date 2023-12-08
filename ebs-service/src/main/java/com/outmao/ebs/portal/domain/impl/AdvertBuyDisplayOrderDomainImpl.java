package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.AdvertBuyDisplayOrderDao;
import com.outmao.ebs.portal.domain.AdvertBuyDisplayOrderDomain;
import com.outmao.ebs.portal.dto.AdvertBuyDisplayOrderDTO;
import com.outmao.ebs.portal.dto.SetAdvertBuyDisplayOrderStatusDTO;
import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class AdvertBuyDisplayOrderDomainImpl extends BaseDomain implements AdvertBuyDisplayOrderDomain {


    @Autowired
    private AdvertBuyDisplayOrderDao advertBuyDisplayOrderDao;

    @Transactional
    @Override
    public AdvertBuyDisplayOrder saveAdvertBuyDisplayOrder(AdvertBuyDisplayOrderDTO request) {

        AdvertBuyDisplayOrder order=new AdvertBuyDisplayOrder();
        order.setCreateTime(new Date());

        BeanUtils.copyProperties(request,order);

        order.setUpdateTime(new Date());

        advertBuyDisplayOrderDao.save(order);

        return order;
    }

    @Transactional
    @Override
    public AdvertBuyDisplayOrder setAdvertBuyDisplayOrderStatus(SetAdvertBuyDisplayOrderStatusDTO request) {
        AdvertBuyDisplayOrder order=advertBuyDisplayOrderDao.findByOrderNoLock(request.getOrderNo());
        order.setStatus(request.getStatus());
        order.setUpdateTime(new Date());
        advertBuyDisplayOrderDao.save(order);
        return order;
    }


}
