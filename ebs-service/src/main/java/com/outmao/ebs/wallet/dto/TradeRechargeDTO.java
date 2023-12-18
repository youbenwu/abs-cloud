package com.outmao.ebs.wallet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TradeRechargeDTO {

    private Long walletId;

    private String currencyId;

    private long amount;

    private String remark;

}
