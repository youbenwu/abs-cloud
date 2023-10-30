package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.entity.RechargeAmount;
import lombok.Data;

@Data
public class RechargeDTO {

    private Long walletId;

    //需要充值的货币
    private RechargeAmount rechargeAmount;

    //备注
    private String remark;
}
