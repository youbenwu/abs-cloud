package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface CashDao extends JpaRepository<Cash,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public Cash findByCashNo(String cashNo);

}
