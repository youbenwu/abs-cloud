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

    /**
     *
     * 订单状态备注
     *
     */
    @ApiModelProperty(name = "statusRemark", value = "订单状态备注")
    private String statusRemark;


}
