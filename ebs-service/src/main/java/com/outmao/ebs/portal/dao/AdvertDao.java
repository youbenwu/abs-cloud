package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AdvertDao extends JpaRepository<Advert,Long> , QuerydslPredicateExecutor<Advert> {



}
