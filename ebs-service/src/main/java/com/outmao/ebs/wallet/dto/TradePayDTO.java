package com.outmao.ebs.wallet.dto;

import lombok.Data;

@Data
public class TradePayDTO {

    private String tradeNo;//交易号
    private int payChannel;//交易矩道
    private int outPayType;//外部支付方式
    private long receiptAmount;//实收金额


}
