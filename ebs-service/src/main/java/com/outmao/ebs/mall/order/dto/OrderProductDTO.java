package com.outmao.ebs.mall.order.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "OrderProductDTO", description = "订单商品参数")
@Data
public class OrderProductDTO {

    @ApiModelProperty(name = "productType", value = "商品类型")
    private Integer productType;

    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;

    @ApiModelProperty(name = "productTitle", value = "商品名称")
    private String productTitle;

    @ApiModelProperty(name = "productImage", value = "商品图片称")
    private String productImage;

    @ApiModelProperty(name = "skuId", value = "sku ID")
    private Long skuId;

    @ApiModelProperty(name = "skuName", value = "sku 名称")
    private String skuName;

    @ApiModelProperty(name = "price", value = "商品单价")
    private double price;

    @ApiModelProperty(name = "quantity", value = "商品数量")
    private int quantity;

    @ApiModelProperty(name = "amount", value = "金额小计")
    private double amount;

    @ApiModelProperty(name = "remark", value = "客户商品备注信息")
    private String remark;


}
