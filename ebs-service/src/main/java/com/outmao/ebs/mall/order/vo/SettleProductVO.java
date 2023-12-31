package com.outmao.ebs.mall.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SettleProductVO {



//    @ApiModelProperty(name = "productType", value = "商品类型")
//    private Integer productType;

    /**
     * 商品Id
     */
    @ApiModelProperty(name = "productId", value = "商品Id")
    private Long productId;

    /**
     * 商品名称
     */
    @ApiModelProperty(name = "productTitle", value = "商品名称")
    private String productTitle;

    /**
     * 商品图片
     */
    @ApiModelProperty(name = "productImage", value = "商品图片")
    private String productImage;

    /**
     * 商品SKU ID
     */
    @ApiModelProperty(name = "skuId", value = "商品SKU ID")
    private Long skuId;

    /**
     * 商品SKU
     */
    @ApiModelProperty(name = "skuName", value = "商品SKU")
    private String skuName;

    /**
     * 商品单价
     */
    @ApiModelProperty(name = "price", value = "商品单价")
    private double price;

    /**
     *
     * 商品数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "商品数量")
    private int quantity;

    /**
     * 金额小计
     */
    @ApiModelProperty(name = "amount", value = "金额小计")
    private double amount;

    /**
     *
     * 重量小计
     *
     */
    @ApiModelProperty(name = "weight", value = "重量小计")
    private Double weight;

    /**
     *
     * 体积小计
     *
     */
    @ApiModelProperty(name = "volume", value = "体积小计")
    private Double volume;

//    /**
//     * 客户商品备注信息
//     */
//    @ApiModelProperty(name = "remark", value = "客户商品备注信息")
//    private String remark;

    @ApiModelProperty(name = "lease", value = "租赁信息")
    private SettleProductLeaseVO lease;


}
