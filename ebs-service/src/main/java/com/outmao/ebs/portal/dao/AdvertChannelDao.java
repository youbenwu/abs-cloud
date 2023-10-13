package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AdvertChannelDao extends JpaRepository<AdvertChannel,Long>, QuerydslPredicateExecutor<AdvertChannel> {

    public AdvertChannel findByCode(String code);

}
