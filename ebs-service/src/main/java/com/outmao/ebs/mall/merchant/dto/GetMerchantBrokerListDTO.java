package com.outmao.ebs.mall.merchant.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetMerchantBrokerListDTO", description = "获取商家经纪人列表参数")
@Data
public class GetMerchantBrokerListDTO {

    @ApiModelProperty(name = "merchantId", value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "keyword", value = "搜索关键字")
    private String keyword;

}
