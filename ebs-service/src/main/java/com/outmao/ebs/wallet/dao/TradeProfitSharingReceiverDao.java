package com.outmao.ebs.wallet.dao;

import com.outmao.ebs.wallet.entity.TradeProfitSharingReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeProfitSharingReceiverDao extends JpaRepository<TradeProfitSharingReceiver,Long> {

    public List<TradeProfitSharingReceiver> findAllBySharingId(Long sharingId);

}
