package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.dto.SoldProductDTO;
import com.outmao.ebs.mall.product.entity.SoldProduct;

public interface SoldProductDomain {

    public SoldProduct saveSoldProduct(SoldProductDTO request);


}
