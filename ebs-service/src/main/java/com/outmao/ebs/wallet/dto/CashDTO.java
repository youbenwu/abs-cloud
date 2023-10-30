package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.entity.CashBankAccount;
import lombok.Data;


@Data
public class CashDTO {

    private Long walletId;

    private double amount;

    private CashBankAccount bankAccount;

    private String remark;

}
