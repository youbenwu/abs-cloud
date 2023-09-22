package com.outmao.ebs.wallet.common.listener;


import com.outmao.ebs.wallet.entity.Trade;

public interface TradeStatusListener {

    public void statusChanged(Trade trade);


}
