package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.product.dao.SoldProductDao;
import com.outmao.ebs.mall.product.domain.SoldProductDomain;
import com.outmao.ebs.mall.product.dto.SoldProductDTO;
import com.outmao.ebs.mall.product.entity.SoldProduct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class SoldProductDomainImpl extends BaseDomain implements SoldProductDomain {

    @Autowired
    private SoldProductDao soldProductDao;

    @Transactional
    @Override
    public SoldProduct saveSoldProduct(SoldProductDTO request) {
        SoldProduct p=new SoldProduct();
        p.setCreateTime(new Date());
        p.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,p);
        soldProductDao.save(p);
        return p;
    }





}
