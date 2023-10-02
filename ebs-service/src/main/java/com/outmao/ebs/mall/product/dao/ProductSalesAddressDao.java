package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductSalesAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSalesAddressDao extends JpaRepository<ProductSalesAddress,Long> {

    public void deleteByProductId(Long productId);

}
