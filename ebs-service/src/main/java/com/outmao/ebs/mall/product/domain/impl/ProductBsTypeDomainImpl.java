package com.outmao.ebs.mall.product.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.product.dao.ProductBsTypeDao;
import com.outmao.ebs.mall.product.domain.ProductBsTypeDomain;
import com.outmao.ebs.mall.product.entity.ProductBsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class ProductBsTypeDomainImpl extends BaseDomain implements ProductBsTypeDomain {

    @Autowired
    private ProductBsTypeDao productBsTypeDao;


    @Transactional
    @Override
    public ProductBsType saveProductBsType(ProductBsType request) {
        ProductBsType type=productBsTypeDao.save(request);
        return type;
    }

    @Transactional
    @Override
    public void deleteProductBsType(Integer type) {
        productBsTypeDao.deleteById(type);
    }

    @Override
    public List<ProductBsType> getProductBsTypeList() {
        return productBsTypeDao.findAll();
    }


}
