package com.outmao.ebs.wallet.dto;


import lombok.Data;

@Data
public class RegisterWalletDTO extends WalletDTO {


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



}
