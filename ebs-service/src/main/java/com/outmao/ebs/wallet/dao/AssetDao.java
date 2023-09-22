package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;


public interface AssetDao  extends JpaRepository<Asset,Long>, QuerydslPredicateExecutor<Asset> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public Asset findByWalletIdAndCurrencyId(Long walletId, String currencyId);


}
