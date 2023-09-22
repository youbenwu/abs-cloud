package com.outmao.ebs.data.dao;


import com.outmao.ebs.data.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BrandDao extends JpaRepository<Brand,Long>, QuerydslPredicateExecutor<Brand> {

    public Brand findByName(String name);

}
