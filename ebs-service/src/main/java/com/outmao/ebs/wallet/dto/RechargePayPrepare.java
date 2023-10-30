package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.common.constant.PayChannel;
import lombok.Data;

@Data
public class RechargePayPrepare {

    private String orderNo;

    private String payChannel= PayChannel.WalletPay.name();


}
