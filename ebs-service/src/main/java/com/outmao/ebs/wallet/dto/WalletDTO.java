package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class WalletDTO {

    /**
     * 钱包ID
     */
    private Long id;

    /**
     * 钱包用户
     */
    private Long userId;

    /**
     *
     * 钱包类型 0--个人钱包 1--商户钱包
     *
     */
    private int type;

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
     * 钱包密码
     */
    private String password;



}
