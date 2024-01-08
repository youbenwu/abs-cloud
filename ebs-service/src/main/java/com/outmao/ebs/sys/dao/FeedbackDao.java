package com.outmao.ebs.sys.dao;

import com.outmao.ebs.sys.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedbackDao extends JpaRepository<Feedback,Long> , QuerydslPredicateExecutor<Feedback> {
}
