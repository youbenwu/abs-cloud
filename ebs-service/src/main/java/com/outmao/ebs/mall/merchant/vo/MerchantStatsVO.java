package com.outmao.ebs.mall.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantStatsVO {

    private Long merchantId;

    @ApiModelProperty(name = "storeCount", value = "门店数量")
    private Long storeCount;

    @ApiModelProperty(name = "brokerCount", value = "经纪人数量")
    private Long brokerCount;

    @ApiModelProperty(name = "partnerCount", value = "合伙人数量")
    private Long partnerCount;

    @ApiModelProperty(name = "customerCount", value = "客户数量")
    private Long customerCount;

    @ApiModelProperty(name = "orderCount", value = "订单数量")
    private Long orderCount;

    @ApiModelProperty(name = "orderAmount", value = "订单金额")
    private Double orderAmount;

    @ApiModelProperty(name = "lookCount", value = "带看数量")
    private Long lookCount;

}
