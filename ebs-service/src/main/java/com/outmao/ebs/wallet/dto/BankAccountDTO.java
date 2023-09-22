package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class BankAccountDTO {

    /**
     * 自动ID
     */
    private Long id;

    /**
     * 钱包用户
     */
    private Long userId;

    /**
     * 0--个人 1--公司
     */
    private int type;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 支行名称
     */
    private String branchBankName;

    /**
     * 户名
     */
    private String accountName;

    /**
     * 帐号
     */
    private String accountNumber;

    /**
     * 备注
     */
    private String remark;


}
