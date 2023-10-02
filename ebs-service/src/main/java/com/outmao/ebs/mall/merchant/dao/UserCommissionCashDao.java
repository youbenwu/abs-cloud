package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.UserCommissionCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface UserCommissionCashDao extends JpaRepository<UserCommissionCash,Long> {


    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from UserCommissionCash o where o.id=?1")
    public UserCommissionCash findByIdForUpdate(Long id);


}
