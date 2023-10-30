package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface RechargeDao extends JpaRepository<Recharge,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public Recharge findByOrderNo(String orderNo);

}
