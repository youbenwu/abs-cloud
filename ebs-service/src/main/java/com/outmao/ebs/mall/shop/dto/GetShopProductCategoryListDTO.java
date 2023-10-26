package com.outmao.ebs.mall.shop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "GetShopProductCategoryListDTO", description = "获取店铺商品分类列表")
@Data
public class GetShopProductCategoryListDTO {


    @ApiModelProperty(name = "shopId", value = "店铺ID")
    private Long shopId;


    @ApiModelProperty(name = "productType", value = "商品类型 30--酒店干洗服务 31--酒店送餐服务")
    private Integer productType;


}
