package com.outmao.ebs.wallet.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;


@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SimpleTransferVO {


    /**
     * 自动ID
     */
    private Long id;

    /**
     * 所属交易
     */
    private Long tradeId;

    /**
     * 交易的币种
     */
    private String currencyId;

    /**
     *
     * 0--支出 1--收入
     *
     */
    private int type;

    /**
     *
     * 转帐金额
     *
     */
    private long amount;

    /**
     *
     * 钱包佘额
     *
     */
    private long balance;

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


    /**
     * 转帐备注
     *
     */
    private String remark;

    /**
     * 转帐发生时间
     *
     */
    private Date createTime;


}
