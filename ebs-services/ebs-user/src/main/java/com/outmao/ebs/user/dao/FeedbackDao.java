package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedbackDao extends JpaRepository<Feedback,Long> , QuerydslPredicateExecutor<Feedback> {
}
