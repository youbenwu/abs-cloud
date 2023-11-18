package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;

public interface EnterpriseDao extends JpaRepository<Enterprise,Long>, QuerydslPredicateExecutor<Enterprise> {


    public List<Enterprise> findAllByUserId(Long userId);

    public Enterprise findByEnterpriseName(String enterpriseName);



}
