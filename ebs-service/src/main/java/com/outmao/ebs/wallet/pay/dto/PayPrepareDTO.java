package com.outmao.ebs.wallet.pay.dto;


import lombok.Data;

@Data
public class PayPrepareDTO {

    private String tradeNo;
    private int payChannel;
    private int outPayType;
    private String clientIp;

}
