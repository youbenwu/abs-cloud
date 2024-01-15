package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProductAddressDao extends JpaRepository<ProductAddress,Long> {

    public ProductAddress findByProductId(Long productId);

    public void deleteByProductId(Long productId);
    public void deleteAllByProductIdIn(Collection<Long> productIdIn);


}
