package com.outmao.ebs.mall.merchant.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "UserCommissionVO", description = "用户佣金")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserCommissionVO {

    /**
     * 自动编号
     */
    @ApiModelProperty(name = "id", value = "自动编号")
    private Long id;

    /**
     *
     * 可提现佣金
     *
     */
    @ApiModelProperty(name = "amount", value = "可提现佣金")
    private double amount;

    /**
     *
     * 佣金总额
     *
     */
    @ApiModelProperty(name = "totalAmount", value = "佣金总额")
    private double totalAmount;


    /**
     *
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

}
