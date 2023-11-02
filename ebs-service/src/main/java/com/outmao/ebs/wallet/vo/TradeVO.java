package com.outmao.ebs.wallet.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TradeVO {

    /**
     * 自动ID
     */
    private Long id;

    /**
     * 交易发起方
     */
    private Long fromId;

    /**
     * 交易对方
     */
    private Long toId;

    /**
     * 交易的币种
     */

    private String currencyId;

    /**
     *
     * 交易状态
     */
    private int status;

    /**
     * 状态备注
     */
    private String statusRemark;

    /**
     * 交易类型
     *
     */
    private int type;

    /**
     * 自定义业务类型
     *
     */
    private int businessType;

    private String subject;

    private String body;

    /**
     *
     * 自定义业务说明
     *
     */
    private String business;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 支付渠道
     */
    private int payChannel;


    /**
     * 交易金额
     */
    private long amount;

    /**
     * 手续费
     */
    private long fee;

    /**
     * 支出的总金额、包括交易金额和手续费
     */
    private long totalAmount;

    /**
     * 实际已支付出的金额
     */
    private long payAmount;

    /**
     * 已退金额、不包括退的手续费
     */
    private long refundAmount;

    /**
     * 退款，是否原路退回，否则退回钱包
     */
    private boolean refundOut;

    /**
     * 交易的备注
     */
    private String remark;

    /**
     * 交易的开始时间
     */
    private Date createTime;

    /**
     * 交易成功时间
     */
    private Date successTime;

    /**
     * 交易完成时间
     */
    private Date finishTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

}
