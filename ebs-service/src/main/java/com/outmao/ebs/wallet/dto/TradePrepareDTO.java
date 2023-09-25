package com.outmao.ebs.wallet.dto;

import lombok.Data;

@Data
public class TradePrepareDTO {

    private String tradeNo;//交易号
    private Long fromId;//发起钱包
    private Long toId;//目标钱包
    private int type;//交易类型
    private int payChannel;//交易矩道
    private int outPayType;
    private String currencyId;//交易货币
    private long amount;//交易额
    private long fee;//手续费
    private int businessType;//业务描述
    private String business;//业务描述
    private String remark;//用户备注
    private String clientIp;//用户IP

}
