package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetUserCommissionCashStatusDTO", description = "设置佣金提现状态")
@Data
public class SetUserCommissionCashStatusDTO {


    /**
     *
     * ID
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;


    /**
     *
     *
     * 状态 0--未处理 1--处理中 2--已退款 3--不通过
     *
     */
    @ApiModelProperty(name = "status", value = "状态 0--未处理 1--处理中 2--已退款 3--不通过")
    private int status;


    /**
     *
     *
     * 状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "状态备注")
    private String statusRemark;



}
