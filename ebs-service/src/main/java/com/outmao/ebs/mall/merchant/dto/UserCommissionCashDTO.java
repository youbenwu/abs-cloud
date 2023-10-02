package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "UserCommissionCashDTO", description = "佣金提现")
@Data
public class UserCommissionCashDTO {


    /**
     *
     * 佣金ID
     *
     */
    private Long commissionId;

    /**
     *
     * 用户ID
     *
     */
    private Long userId;


    /**
     *
     *
     * 提现佣金
     *
     */
    private double amount;


    /**
     *
     *
     * 开户银行
     *
     */
    private String bankName;

    /**
     *
     *
     * 开户名
     *
     */
    private String bankAccount;

    /**
     *
     *
     * 银行卡号
     *
     */
    private String bankCardNumber;


    /**
     *
     *
     * 备注
     *
     */
    private String remark;



}
