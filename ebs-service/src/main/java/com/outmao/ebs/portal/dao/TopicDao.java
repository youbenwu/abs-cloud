package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TopicDao extends JpaRepository<Topic,Long> , QuerydslPredicateExecutor<Topic> {

}
