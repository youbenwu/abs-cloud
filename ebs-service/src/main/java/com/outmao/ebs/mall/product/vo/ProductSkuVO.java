package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "ProductSkuVO", description = "商品SKU")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductSkuVO {

    /**
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     * 属性所在的商品
     *
     */
    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;

    /**
     *
     * SKU名称
     *
     */
    @ApiModelProperty(name = "name", value = "SKU名称")
    private String name;

    /**
     *
     * SKU标签
     *
     */
    @ApiModelProperty(name = "marks", value = "SKU标签")
    private String marks;

    /**
     *
     * SKU编号
     *
     */
    @ApiModelProperty(name = "skuNo", value = "SKU编号")
    private String skuNo;

    /**
     *
     * 商品图片
     *
     */
    @ApiModelProperty(name = "image", value = "SKU图片")
    private String image;

    /**
     *
     * 商品图片列表
     *
     */
    @ApiModelProperty(name = "images", value = "SKU图片列表")
    private List<ProductImageVO> images;

    /**
     *
     * 用规格List<ProductSkuPropertyDTO> ID生成
     *
     */
    @ApiModelProperty(name = "key", value = "用规格List<ProductSkuPropertyDTO> ID生成")
    private String key;

    /**
     *
     * 规格值 JSON
     *
     */
    @ApiModelProperty(name = "value", value = "规格值 List<ProductSkuPropertyDTO> JSON字符串")
    private String value;


    /**
     * 商品价格
     */
    @ApiModelProperty(name = "price", value = "商品价格")
    private Double price;

    /**
     *
     * 单价（房屋销售里的每平方米单价）
     *
     */
    @ApiModelProperty(name = "unitPrice", value = "单价（房屋销售里的每平方米单价）")
    private Double unitPrice;

    /**
     * 商品库存
     */
    @ApiModelProperty(name = "stock", value = "商品库存")
    private Long stock;

    /**
     * 告警库存
     */
    @ApiModelProperty(name = "alarmStock", value = "告警库存")
    private Long alarmStock;


}
