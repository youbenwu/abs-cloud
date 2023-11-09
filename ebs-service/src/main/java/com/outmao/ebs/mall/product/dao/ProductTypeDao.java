package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeDao extends JpaRepository<ProductType,Long> {

    public boolean existsByName(String name);

}
