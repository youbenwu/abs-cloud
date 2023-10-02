package com.outmao.ebs.mall.shop.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetShopStatusDTO", description = "设置店铺状态")
@Data
public class SetShopStatusDTO {


    @ApiModelProperty(name = "id", value = "店铺ID")
    private Long id;


    @ApiModelProperty(name = "status", value = "状态0-未审核 1-正常 2-禁用",required = true)
    private int status;


    @ApiModelProperty(name = "statusRemark", value = "状态",required = true)
    private String statusRemark;


}
