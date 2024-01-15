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
    private int businessType;//业务描述
    private String business;//业务描述
    private String remark;//交易备注private String remark;

    public TradeRechargeDTO(Long walletId,String currencyId,long amount,String remark){
        this.walletId=walletId;
        this.currencyId=currencyId;
        this.amount=amount;
        this.remark=remark;
    }

}
