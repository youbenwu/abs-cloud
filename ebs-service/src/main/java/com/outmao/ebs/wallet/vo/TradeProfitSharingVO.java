package com.outmao.ebs.wallet.vo;


import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class TradeProfitSharingVO  {



    /**
     * 自动ID
     */
    private Long id;

    /**
     * 交易ID
     */
    private Long tradeId;

    /**
     * 分帐单号
     */
    private String sharingNo;

    /**
     *
     * 状态 1--处理中 2--分帐完成
     *
     */
    private int status;


    /**
     *
     * 是否解冻商户金额
     *
     */
    private boolean unfreeze;

    private List<TradeProfitSharingReceiverVO> receivers;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;



}
