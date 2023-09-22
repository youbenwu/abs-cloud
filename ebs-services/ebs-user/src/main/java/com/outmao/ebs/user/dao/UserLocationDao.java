package com.outmao.ebs.user.dao;


import com.outmao.ebs.user.entity.UserLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface UserLocationDao extends JpaRepository<UserLocation, Long> ,QuerydslPredicateExecutor<UserLocation> {


	public Page<UserLocation> findAllByUserId(Long userId, Pageable pageable);


}


