package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.UserCommission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface UserCommissionDao extends JpaRepository<UserCommission,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    public UserCommission findByTargetId(Long target);


    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from UserCommission o where o.id=?1")
    public UserCommission findByIdForUpdate(Long id);

}

