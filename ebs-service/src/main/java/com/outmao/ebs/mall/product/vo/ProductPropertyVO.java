package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductPropertyVO", description = "商品规格属性")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductPropertyVO {

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
     * 属性KEY
     *
     */
    @ApiModelProperty(name = "key", value = "属性KEY")
    private String key;

    /**
     *
     * 属性名称
     *
     */
    @ApiModelProperty(name = "name", value = "属性名称")
    private String name;

    /**
     *
     * 属性的值 List<ProductPropertyItem> JSON
     *
     */
    @ApiModelProperty(name = "value", value = "属性的值 List<ProductPropertyItemVO> JSON字符串")
    private String value;

//    /**
//     *
//     *
//     * 属性类型 唯一属性  单选属性  复选属性
//     *
//     *
//     */
//    @ApiModelProperty(name = "type", value = "属性类型 0--唯一属性  1--单选属性  2--复选属性")
//    private Integer type;


}
