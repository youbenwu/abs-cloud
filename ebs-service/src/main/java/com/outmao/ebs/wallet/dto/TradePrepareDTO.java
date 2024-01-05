package com.outmao.ebs.wallet.dto;

import lombok.Data;

import java.util.List;

@Data
public class TradePrepareDTO {

    private String tradeNo;//交易号
    private Long fromId;//发起钱包
    private Long toId;//目标钱包
    private String subject;//主题
    private String body;//内容
    private int type;//交易类型
    private int payChannel;//交易矩道
    private int outPayType;//外部支付方式
    private String currencyId;//交易货币
    private long amount;//交易额
    private long fee;//手续费
    private int businessType;//业务描述
    private String business;//业务描述
    private String remark;//用户备注

    //分帐列表
    private List<RoyaltyDTO> royaltys;


}
