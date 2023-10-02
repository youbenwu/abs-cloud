package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetMerchantBrokerStatusDTO", description = "设置商家经纪人状态")
@Data
public class SetMerchantBrokerStatusDTO {


    @ApiModelProperty(name = "id", value = "经纪人ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除")
    private int status;

    @ApiModelProperty(name = "statusRemark", value = "状态")
    private String statusRemark;


}
