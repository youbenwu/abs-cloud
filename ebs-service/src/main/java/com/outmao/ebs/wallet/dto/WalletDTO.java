package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class WalletDTO {

    /**
     * 钱包ID
     */
    private Long id;

    /**
     *
     * 银行帐户ID
     *
     */
    private Long bankAccountId;

    /**
     *
     * 实名
     *
     */
    private String realName;

    /**
     *
     * 绑定手机号
     *
     */
    private String phone;


    /**
     * 钱包密码
     */
    private String password;



}
