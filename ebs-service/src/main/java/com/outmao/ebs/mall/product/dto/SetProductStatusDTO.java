package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetProductStatusDTO", description = "设置商品状态")
@Data
public class SetProductStatusDTO {


    @ApiModelProperty(name = "id", value = "商品ID",required = true)
    private Long id;
    /**
     *
     * 状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)
     *
     */
    @ApiModelProperty(name = "status", value = "状态(0--正常 1--禁用 2--未审核 3--审核中 4--审核失败)")
    private int status;

    @ApiModelProperty(name = "statusRemark", value = "备注")
    private String statusRemark;


}
