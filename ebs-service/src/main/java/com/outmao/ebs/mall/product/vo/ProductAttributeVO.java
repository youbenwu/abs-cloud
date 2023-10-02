package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductAttributeVO", description = "商品参数")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductAttributeVO {

    /**
     *
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 属性所在的商品
     *
     */
    @ApiModelProperty(name = "productId", value = "商品ID")
    private Long productId;

    /**
     *
     * 属性所在的分组
     *
     */
    @ApiModelProperty(name = "groupId", value = "分组ID")
    private Long groupId;

    /**
     *
     * 属性KEY
     *
     */
    @ApiModelProperty(name = "key", value = "参数KEY")
    private String key;

    /**
     *
     * 属性名称
     *
     */
    @ApiModelProperty(name = "name", value = "参数名称")
    private String name;

    /**
     *
     * 属性的值
     *
     */
    @ApiModelProperty(name = "name", value = "参数值")
    private String value;

    /**
     *
     * 用于计算的值
     *
     */
    @ApiModelProperty(name = "v", value = "用于计算的值")
    private String v;


}
