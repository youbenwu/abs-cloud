package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferDao  extends JpaRepository<Transfer,Long> {

    public Transfer findByTradeIdAndToType(Long tradeId, Transfer.TransferType toType);

    public List<Transfer> findAllByActionKey(String actionKey);


}
