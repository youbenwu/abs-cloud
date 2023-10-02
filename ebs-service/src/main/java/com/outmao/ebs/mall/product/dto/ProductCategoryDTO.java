package com.outmao.ebs.mall.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "ProductCategoryDTO", description = "保存商品分类参数")
@Data
public class ProductCategoryDTO {


    @ApiModelProperty(name = "id", value = "分类ID")
    private Long id;

    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;

    @ApiModelProperty(name = "parentId", value = "上级ID")
    private Long parentId;

    @ApiModelProperty(name = "sort", value = "分类排序")
    private int sort;

    @ApiModelProperty(name = "image", value = "分类图片")
    private String image;

    @ApiModelProperty(name = "title", value = "分类标题",required = true)
    private String title;

    @ApiModelProperty(name = "description", value = "分类描述")
    private String description;


    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "productType", value = "商品类型")
    private int productType;




}
