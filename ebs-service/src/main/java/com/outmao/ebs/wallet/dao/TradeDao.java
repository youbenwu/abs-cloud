package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.Date;

public interface TradeDao  extends JpaRepository<Trade,Long> {

	@Lock(value = LockModeType.PESSIMISTIC_READ)
	@Query("select t from Trade t where t.tradeNo=?1")
	public Trade findByTradeNoLock(String tradeNo);

	public Trade findByTradeNo(String tradeNo);

	@Query("select t.tradeNo from Trade t where t.status=0 and t.timeoutTime is not null and t.timeoutTime<?1")
	public Collection<String> findAllTradeNoByTimeoutTimeBefore(Date time);

	@Query("select t.tradeNo from Trade t where t.status=1 and t.finishTimeoutTime is not null and t.finishTimeoutTime<?1")
	public Collection<String> findAllTradeNoByFinishTimeoutTimeBefore(Date time);

	@Query("select t.tradeNo from Trade t where t.freezeStatus=1 and t.freezeExpireTime is not null and t.freezeExpireTime<?1")
	public Collection<String> findAllTradeNoByFreezeExpireTimeBefore(Date time);

}
