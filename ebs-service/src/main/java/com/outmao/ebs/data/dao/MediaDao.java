package com.outmao.ebs.data.dao;


import com.outmao.ebs.data.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MediaDao extends JpaRepository<Media,Long> , QuerydslPredicateExecutor<Media> {

    public Media findByUuid(String uuid);



}
