package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetMerchantPartnerListDTO", description = "获取商家合伙人列表")
@Data
public class GetMerchantPartnerListDTO {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    @ApiModelProperty(name = "keyword", value = "搜索关键字")
    private String keyword;


}
