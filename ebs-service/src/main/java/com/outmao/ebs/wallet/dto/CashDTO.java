package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.entity.CashBankAccount;
import lombok.Data;


@Data
public class CashDTO {

    private Long walletId;

    private long amount;

    private CashBankAccount to;

    private String remark;

}
