package com.outmao.ebs.wallet.dto;


import lombok.Data;
/**
 *
 * 分帐
 *
 */
@Data
public class RoyaltyDTO {


    /**
     *
     * 钱包ID
     *
     */
    private Long walletId;

    /**
     *
     * 分帐金额
     *
     */
    private long amount;

    /**
     * 自定义业务类型
     *
     */
    private int businessType;

    /**
     *
     * 自定义业务说明
     *
     */
    private String business;

}
