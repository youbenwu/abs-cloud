package com.outmao.ebs.mall.shop.dao;

import com.outmao.ebs.mall.shop.entity.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ShopAddressDao extends JpaRepository<ShopAddress,Long> , QuerydslPredicateExecutor<ShopAddress> {



}
