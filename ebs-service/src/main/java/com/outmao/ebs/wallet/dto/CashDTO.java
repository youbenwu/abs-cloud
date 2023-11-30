package com.outmao.ebs.wallet.dto;


import com.outmao.ebs.wallet.entity.CashAlipayAccount;
import com.outmao.ebs.wallet.entity.CashBankAccount;
import lombok.Data;



@Data
public class CashDTO {


    private Long walletId;

    //提现金额
    private double amount;

    //支付宝帐号

    private CashAlipayAccount alipayAccount;

    //银行卡
    private CashBankAccount bankAccount;

    private String remark;


}
