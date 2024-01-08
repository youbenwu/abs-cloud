package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.FeedbackItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedbackItemDao extends JpaRepository<FeedbackItem,Long>, QuerydslPredicateExecutor<FeedbackItem> {


}
