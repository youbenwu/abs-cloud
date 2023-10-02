package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetMerchantCustomerListDTO", description = "获取商家客户列表参数")
@Data
public class GetMerchantCustomerListDTO {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "keyword", value = "搜索关键字")
    private String keyword;

    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    @ApiModelProperty(name = "partnerId", value = "兼职经纪人ID")
    private Long partnerId;


}
