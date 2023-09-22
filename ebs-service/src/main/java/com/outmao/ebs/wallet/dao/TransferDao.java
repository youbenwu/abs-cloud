package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferDao  extends JpaRepository<Transfer,Long> {

    public Transfer findByTradeIdAndToType(Long tradeId, Transfer.TransferType toType);


}
