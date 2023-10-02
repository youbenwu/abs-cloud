package com.outmao.ebs.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GiftsProductSkuDTO {

    @ApiModelProperty(name = "skuId", value = "商品SKU ID")
    private Long skuId;

    /**
     *
     * 赠品数量 最少1
     *
     */
    @ApiModelProperty(name = "quantity", value = "赠品数量 最少1")
    private int quantity=1;

}
