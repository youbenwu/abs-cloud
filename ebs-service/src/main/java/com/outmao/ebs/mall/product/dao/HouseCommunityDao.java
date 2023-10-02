package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.HouseCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HouseCommunityDao extends JpaRepository<HouseCommunity,Long>, QuerydslPredicateExecutor<HouseCommunity> {



}
