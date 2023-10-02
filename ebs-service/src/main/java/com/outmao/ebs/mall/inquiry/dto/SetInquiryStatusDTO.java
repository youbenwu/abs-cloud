package com.outmao.ebs.mall.inquiry.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 用户询盘
 *
 */
@ApiModel(value = "SetInquiryStatusDTO", description = "设置询盘状态")
@Data
public class SetInquiryStatusDTO {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "状态 0--未回访 1--已回访")
    private int status;

    @ApiModelProperty(name = "statusRemark", value = "状态备注")
    private String statusRemark;


}
