package com.outmao.ebs.mall.inquiry.dao;

import com.outmao.ebs.mall.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InquiryDao extends JpaRepository<Inquiry,Long> , QuerydslPredicateExecutor<Inquiry> {



}
