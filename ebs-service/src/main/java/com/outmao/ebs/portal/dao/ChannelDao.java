package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ChannelDao extends JpaRepository<Channel,Long> , QuerydslPredicateExecutor<Channel> {
}
