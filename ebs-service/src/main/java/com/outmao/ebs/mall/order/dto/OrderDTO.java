package com.outmao.ebs.mall.order.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel(value = "OrderDTO", description = "保存订单参数")
@Data
public class OrderDTO {


    @ApiModelProperty(name = "shopId", value = "店铺ID",required = true)
    private Long shopId;

    @ApiModelProperty(name = "userId", value = "下单用户ID",required = true)
    private Long userId;

    @ApiModelProperty(name = "storeId", value = "门店ID")
    private Long storeId;

    @ApiModelProperty(name = "lookId", value = "关联带看ID")
    private Long lookId;

    /**
     * 商品类型
     */
    @ApiModelProperty(name = "type", value = "商品类型  " +
            "0--普通商品 " +
            "1--虚拟商品 " +
            "11--新楼盘 " +
            "12--二手房 " +
            "13--出租房 " +
            "20--广告频道 " +
            "30--酒店服务 " +
            "31--酒店设备 " +
            "100--外部携程旅游商品")
    private Integer type;

    @ApiModelProperty(name = "products", value = "订单商品信息",required = true)
    private List<OrderProductDTO> products;

    @ApiModelProperty(name = "amount", value = "商品金额")
    private double amount;

    @ApiModelProperty(name = "freight", value = "运费 显示给用户看的运费")
    private double freight;

    @ApiModelProperty(name = "totalAmount", value = "订单金额,实际支付金额")
    private double totalAmount;

    @ApiModelProperty(name = "address", value = "订单收货地址")
    private OrderAddressDTO address;

    @ApiModelProperty(name = "remark", value = "客户备注")
    private String remark;

    @ApiModelProperty(name = "payChannel", value = "订单支付渠道")
    private String payChannel;

    @ApiModelProperty(name = "contracts", value = "交易合同")
    private List<OrderContractDTO> contracts;


}
