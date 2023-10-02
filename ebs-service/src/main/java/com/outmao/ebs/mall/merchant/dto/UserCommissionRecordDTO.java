package com.outmao.ebs.mall.merchant.dto;



import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *
 * 用户佣金记录
 *
 * */
@ApiModel(value = "UserCommissionRecordDTO", description = "用户佣金记录")
@Data
public class UserCommissionRecordDTO {

    /**
     *
     * 佣金ID
     *
     */
    private Long commissionId;

    /**
     *
     * 关联的订单ID
     *
     */
    private Long orderId;


    /**
     *
     * 0--直接收益 1--子级产生的收益
     *
     */
    private int level;

    /**
     *
     * 佣金
     *
     */
    private double amount;


    /**
     *
     * 类型 0--订单收益 1--提现 2--提现取消
     *
     */
    private int type;

    /**
     *
     * 显示图片
     *
     */
    private String image;


    /**
     *
     * 显示备注
     *
     */
    private String remark;


}
