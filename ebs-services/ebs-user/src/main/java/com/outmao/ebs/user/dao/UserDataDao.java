package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UserDataDao extends JpaRepository<UserData,Long>, QuerydslPredicateExecutor<UserData> {

    public UserData findByUserIdAndName(Long userId, String name);

    public List<UserData> findAllByUserIdAndType(Long userId, String type);



}
