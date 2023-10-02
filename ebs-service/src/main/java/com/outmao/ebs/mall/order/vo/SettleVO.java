package com.outmao.ebs.mall.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class SettleVO {

    private Long id;

    private Long userId;

    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "lookId", value = "关联带看ID")
    private Long lookId;

    @ApiModelProperty(name = "address", value = "收货地址")
    private SettleAddressVO address;

    @ApiModelProperty(name = "quantity", value = "商品总数量")
    private int quantity;

    @ApiModelProperty(name = "amount", value = "商品总价")
    private double amount;

    @ApiModelProperty(name = "freight", value = "运费 显示给用户看的运费")
    private double freight;

    @ApiModelProperty(name = "totalAmount", value = "订单支付金额")
    private double totalAmount;

    @ApiModelProperty(name = "payChannel", value = "订单支付渠道")
    private String payChannel;

    @ApiModelProperty(name = "shops", value = "按店铺分组结算")
    private List<SettleShopVO> shops;

}
