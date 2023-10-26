package com.outmao.ebs.mall.order.dto;

import com.outmao.ebs.wallet.common.constant.PayChannel;
import lombok.Data;

@Data
public class OrderPayPrepare {

    private String orderNo;

    private String payChannel= PayChannel.WalletPay.name();

    private String currency="RMB";

}
