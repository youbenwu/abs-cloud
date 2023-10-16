package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetMerchantStatusDTO", description = "设置商家状态")
@Data
public class SetMerchantStatusDTO {

    @ApiModelProperty(name = "id", value = "商家ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败 5--审核成功")
    private int status;

    @ApiModelProperty(name = "statusRemark", value = "状态备注")
    private String statusRemark;

}
