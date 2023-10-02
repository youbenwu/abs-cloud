package com.outmao.ebs.mall.merchant.vo;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 *
 *
 *
 * */
@Data
public class MerchantPartnerStatsVO implements Serializable {


    /**
     *
     */
    private Long partnerId;


    @ApiModelProperty(name = "customerCount", value = "一级客户数")
    private long customerCount;

    @ApiModelProperty(name = "childrenCustomerCount", value = "二级客户数")
    private long childrenCustomerCount;

    @ApiModelProperty(name = "orderCount", value = "一级订单数")
    private long orderCount;

    @ApiModelProperty(name = "childrenOrderCount", value = "二级订单数")
    private long childrenOrderCount;

    @ApiModelProperty(name = "orderAmount", value = "一级订单金额")
    private double orderAmount;

    @ApiModelProperty(name = "childrenOrderAmount", value = "二级订单金额")
    private double childrenOrderAmount;


}
