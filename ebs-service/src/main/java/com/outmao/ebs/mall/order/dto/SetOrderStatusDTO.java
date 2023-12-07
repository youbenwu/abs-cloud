package com.outmao.ebs.mall.order.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetOrderStatusDTO", description = "设置订单状态")
@Data
public class SetOrderStatusDTO {

    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    @ApiModelProperty(name = "status", value = "订单状态\n" +
            "      00 待付款：用户下单未付款状态\n" +
            "      10 待发货：用户付款商家未发货状态\n" +
            "      20 待签收：商家发货用户未签收状态\n" +
            "      30 已完成：用户签收交易完成状态\n" +
            "      40 退款中：用户发起退款申请进入该状态\n" +
            "      50 已关闭：待付款超时、退款完成进入该状态")
    private int status;


    @ApiModelProperty(name = "statusRemark", value = "订单状态备注")
    private String statusRemark;


    /**
     *
     * 订单子状态
     * 10 待发货：商家未确认
     * 11 待发货：商家已确认
     * 12 待发货：部分发货
     *
     * 20 待签收：商家已发货
     *
     * 30 已完成：超时自动签收
     * 31 已完成：买家签收
     * 32 已完成：商家标记签收
     *
     * 40 已关闭：待付款超时取消
     * 41 已关闭：待付款买家取消
     * 42 已关闭：待付款卖家取消
     * 43 已关闭：全款退款关闭
     *
     *
     */
    @ApiModelProperty(name = "subStatus", value = "订单子状态\n" +
            "     10 待发货：商家未确认\n" +
            "     11 待发货：商家已确认\n" +
            "     12 待发货：部分发货\n" +
            "     20 待签收：商家已发货\n" +
            "     30 已完成：超时自动签收\n" +
            "     31 已完成：买家签收\n" +
            "     32 已完成：商家标记签收\n" +
            "     40 已关闭：待付款超时取消\n" +
            "     41 已关闭：待付款买家取消\n" +
            "     42 已关闭：待付款卖家取消\n" +
            "     43 已关闭：全款退款关闭\n")
    private int subStatus;


    @ApiModelProperty(name = "subStatusRemark", value = "订单子状态备注")
    private String subStatusRemark;


}
