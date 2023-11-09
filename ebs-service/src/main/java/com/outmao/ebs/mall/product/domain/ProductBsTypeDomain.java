package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.entity.ProductBsType;

import java.util.List;

public interface ProductBsTypeDomain {

    public ProductBsType saveProductBsType(ProductBsType request);

    public void deleteProductBsType(Integer type);

    public List<ProductBsType> getProductBsTypeList();

}
