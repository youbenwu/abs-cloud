package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "SetProductStatusDTO", description = "设置商品状态")
@Data
public class SetProductStatusDTO {



    @ApiModelProperty(name = "id", value = "商品ID",required = true)
    private Long id;
    /**
     *
     * 商品状态(0--未上架，1--已上架)
     *
     */
    @ApiModelProperty(name = "status", value = "商品状态(0--未上架，1--已上架)",required = true)
    private int status;




}
