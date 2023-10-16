package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetProductOnSellDTO", description = "设置商品上下架")
@Data
public class SetProductOnSellDTO {


    @ApiModelProperty(name = "id", value = "商品ID",required = true)
    private Long id;


    @ApiModelProperty(name = "onSell", value = "是否上架",required = true)
    private boolean onSell;


}
