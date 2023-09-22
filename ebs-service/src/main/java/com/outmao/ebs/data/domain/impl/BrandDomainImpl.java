package com.outmao.ebs.data.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.data.dao.BrandDao;
import com.outmao.ebs.data.domain.BrandDomain;
import com.outmao.ebs.data.dto.BrandDTO;
import com.outmao.ebs.data.dto.GetBrandListDTO;
import com.outmao.ebs.data.entity.Brand;
import com.outmao.ebs.data.entity.QBrand;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class BrandDomainImpl extends BaseDomain implements BrandDomain {

    @Autowired
    UserDao userDao;

    @Autowired
    BrandDao brandDao;

    @Transactional
    @Override
    public Brand saveBrand(BrandDTO request) {
        Brand brand=request.getId()==null?null:brandDao.getOne(request.getId());

        if(brand==null){
            if(brandDao.findByName(request.getName())!=null){
                throw new BusinessException("品牌已存在");
            }
            brand=new Brand();
            brand.setCreateTime(new Date());
            brand.setUser(userDao.getOne(request.getUserId()));
        }

        BeanUtils.copyProperties(request,brand);
        //候改后设置为未审核状态
        brand.setStatus(Status.NotAudit.getStatus());
        brand.setStatusRemark(Status.NotAudit.getStatusRemark());
        brand.setUpdateTime(new Date());
        brandDao.save(brand);
        return brand;
    }

    @Transactional
    @Override
    public void deleteBrandById(Long id) {
        brandDao.deleteById(id);
    }

    @Transactional
    @Override
    public Brand setBrandStatus(Long id,int status, String statusRemark) {
        Brand brand=brandDao.getOne(id);
        brand.setStatus(status);
        brand.setStatusRemark(statusRemark);
        brandDao.save(brand);
        return brand;
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandDao.findById(id).orElse(null);
    }


    @Override
    public Page<Brand> getBrandPage(GetBrandListDTO request, Pageable pageable) {
        QBrand e= QBrand.brand;
        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }
        if(request.getStatusIn()!=null){
            p=e.status.in(request.getStatusIn()).and(p);
        }
        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.name.like("%"+request.getKeyword()+"%").and(p);
        }
        Page<Brand> page=brandDao.findAll(p,pageable);
        return page;
    }


}
