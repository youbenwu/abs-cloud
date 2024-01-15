package com.outmao.ebs.wallet.dao;

import com.outmao.ebs.wallet.entity.TradeProfitSharing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface TradeProfitSharingDao extends JpaRepository<TradeProfitSharing,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select t from TradeProfitSharing t where t.sharingNo=?1")
    public TradeProfitSharing findBySharingNoLock(String sharingNo);

}
