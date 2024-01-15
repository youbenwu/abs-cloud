package com.outmao.ebs.wallet.dto;

import lombok.Data;

@Data
public class TradeProfitSharingReceiverDTO {

    /**
     * 目标钱包ID
     */
    private Long account;

    /**
     * 分账金额
     */
    private long amount;

    /**
     * 分账备注
     */
    private String remark;

    /**
     *
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
