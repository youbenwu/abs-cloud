package com.outmao.ebs.wallet.vo;

import lombok.Data;


@Data
public class TradeProfitSharingReceiverVO  {


    /**
     * 自动ID
     */
    private Long id;

    /**
     * 交易ID
     */
    private Long tradeId;

    /**
     * 分账单号ID
     */
    private Long sharingId;

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
