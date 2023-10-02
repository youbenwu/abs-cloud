package com.outmao.ebs.mall.order.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "GetOrderListDTO", description = "获取订单列表")
@Data
public class GetOrderListDTO {



    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(name = "userId", value = "下单用户ID")
    private Long userId;

    @ApiModelProperty(name = "brokerId", value = "经纪人ID")
    private Long brokerId;

    @ApiModelProperty(name = "partnerId", value = "合伙人ID")
    private Long partnerId;

    @ApiModelProperty(name = "keyword", value = "关键字")
    private String keyword;

    @ApiModelProperty(name = "status", value = "订单状态订单状态\n" +
            "\t * 00 待付款：用户下单未付款状态\n" +
            "\t * 10 待发货：用户付款商家未发货状态\n" +
            "\t * 20 待签收：商家发货用户未签收状态\n" +
            "\t * 30 已完成：用户签收交易完成状态\n" +
            "\t * 40 已关闭：待付款超时、退款完成进入该状态")
    private Integer status;




}
