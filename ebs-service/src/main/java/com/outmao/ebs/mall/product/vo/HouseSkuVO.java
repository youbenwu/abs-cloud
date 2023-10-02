package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@ApiModel(value = "HouseSkuVO", description = "房源户型参数 一个SKU就是一个户型")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class HouseSkuVO {

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

    /**
     *
     * SKU标签
     *
     */
    @ApiModelProperty(name = "marks", value = "SKU标签")
    private String marks;

    /**
     *
     * 商品图片
     *
     */
    @ApiModelProperty(name = "image", value = "SKU图片")
    private String image;



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
     * 规格值
     *
     */
    @ApiModelProperty(name = "value", value = "户型规格值")
    private HouseSkuPropertyVO value;

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


}
