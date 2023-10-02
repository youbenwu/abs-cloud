package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel(value = "HouseSkuDTO", description = "保存房源户型参数 一个SKU就是一个户型")
@Data
public class HouseSkuDTO {

    /**
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * SKU名称
     *
     */
    @ApiModelProperty(name = "name", value = "户型名称")
    private String name;

    @ApiModelProperty(name = "marks", value = "户型标签")
    private String marks;

    /**
     *
     * sku图片
     *
     */
    @ApiModelProperty(name = "image", value = "户型图片")
    private String image;

    /**
     *
     * sku图片
     *
     */
    @ApiModelProperty(name = "images", value = "户型图片")
    private List<ProductImageDTO> images;

    /**
     *
     * 规格值
     *
     */
    @ApiModelProperty(name = "value", value = "户型规格值")
    private HouseSkuPropertyDTO value;

    /**
     * 商品价格
     */
    @ApiModelProperty(name = "price", value = "商品价格")
    private double price;

    /**
     *
     * 单价（房屋销售里的每平方米单价）
     *
     */
    @ApiModelProperty(name = "unitPrice", value = "每平方米单价")
    private Double unitPrice;

    /**
     * 商品库存
     */
    @ApiModelProperty(name = "stock", value = "商品库存")
    private long stock;


}
