package com.outmao.ebs.mall.merchant.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class MerchantCustomerStatsVO {

    private Long customerId;

    @ApiModelProperty(name = "orderCount", value = "订单数量")
    private Long orderCount;

    @ApiModelProperty(name = "orderAmount", value = "订单金额")
    private Double orderAmount;

    @ApiModelProperty(name = "lookCount", value = "带看数量")
    private Long lookCount;



}
