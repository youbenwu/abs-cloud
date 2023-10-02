package com.outmao.ebs.mall.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductPropertyItemVO", description = "商品规格属性选项")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductPropertyItemVO {

    /**
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 属性的值
     *
     */
    @ApiModelProperty(name = "value", value = "属性的值")
    private String value;

//    /**
//     *
//     * 用于计算的值
//     *
//     */
//    @ApiModelProperty(name = "v", value = "用于计算的值")
//    private String v;


}
