package com.outmao.ebs.mall.inquiry.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 用户询盘
 *
 */
@ApiModel(value = "GetInquiryListDTO", description = "获取询盘列表参数")
@Data
public class GetInquiryListDTO {


    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "keyword", value = "查询关键字")
    private String keyword;

    @ApiModelProperty(name = "userId", value = "查询这个用户发表的询盘")
    private Long userId;

    @ApiModelProperty(name = "status", value = "状态 0--未回访 1--已回访 不传返回全部")
    private Integer status;


}
