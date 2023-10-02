package com.outmao.ebs.mall.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SettleShopVO {

    @ApiModelProperty(name = "shopId", value = "商家店铺")
    private Long shopId;

    @ApiModelProperty(name = "shopName", value = "商家店铺")
    private String shopName;

    //商品信息

    /**
     * 订单商品信息
     */
    @ApiModelProperty(name = "products", value = "订单商品信息")
    private List<SettleProductVO> products;

    /**
     *
     * 商品总数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "商品总数量")
    private int quantity;

    /**
     * 商品总价
     */
    @ApiModelProperty(name = "amount", value = "商品总价")
    private double amount;

    /**
     * 客户备注
     */
//    @ApiModelProperty(name = "remark", value = "客户备注")
//    private String remark;

    //支付信息

    /**
     *
     * 运费 显示给用户看的运费
     *
     */
    @ApiModelProperty(name = "freight", value = "运费 显示给用户看的运费")
    private double freight;

    /**
     * 订单金额,实际支付金额
     */
    @ApiModelProperty(name = "totalAmount", value = "订单金额,实际支付金额")
    private double totalAmount;



}
