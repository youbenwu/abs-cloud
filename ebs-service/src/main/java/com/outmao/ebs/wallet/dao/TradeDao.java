package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface TradeDao  extends JpaRepository<Trade,Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select t from Trade t where t.tradeNo=?1")
	public Trade findByTradeNoLock(String tradeNo);

	public Trade findByTradeNo(String tradeNo);

}
