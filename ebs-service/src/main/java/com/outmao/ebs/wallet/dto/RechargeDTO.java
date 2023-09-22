package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class RechargeDTO {

    private Long walletId;

    //需要充值的货币
    private String currencyId;

    //充值数量
    private long quantity;
    //备注
    private String remark;
}
