package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserAddressDao extends JpaRepository<UserAddress, Long>, QuerydslPredicateExecutor<UserAddress> {

	public void deleteAllByIdIn(List<Long> idIn);

}
