package com.outmao.ebs.wallet.entity;

import lombok.Data;

@Data
public class TradeProfitSharingReceiver {

    private Long tradeId;

    private Long orderId;

    private Long account;

    private long amount;



}
