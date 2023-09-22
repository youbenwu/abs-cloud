package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface TradeDao  extends JpaRepository<Trade,Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	public Trade findByTradeNo(String tradeNo);

}
