package com.outmao.ebs.wallet.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.outmao.ebs.wallet.entity.Transfer;
import lombok.Data;

import java.util.Date;


@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TransferVO {


    /**
     * 自动ID
     */

    private Long id;

    /**
     * 所属交易
     */
    private Long tradeId;

    /**
     * 贷方钱包
     */
    private Long fromId;

    /**
     * 借方钱包
     */
    private Long toId;

    /**
     * 交易的币种
     */
    private String currencyId;


    /**
     *
     * 转帐类型
     *
     */
    private Transfer.TransferType fromType;

    /**
     *
     * 转帐类型
     *
     */
    private Transfer.TransferType toType;


    /**
     *
     * 转帐金额
     *
     */
    private long amount;

    /**
     *
     * 转出后佘额
     *
     */
    private long fromBalance;

    /**
     *
     * 转入后佘额
     *
     */
    private long toBalance;

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
     *
     * 转帐单号
     *
     */
    private String transferNo;

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
