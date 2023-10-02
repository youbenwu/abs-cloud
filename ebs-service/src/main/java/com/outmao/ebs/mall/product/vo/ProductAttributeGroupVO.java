package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "ProductAttributeGroupVO", description = "商品参数分组")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductAttributeGroupVO {

    /**
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
     * 参数KEY
     *
     */
    @ApiModelProperty(name = "key", value = "参数KEY")
    private String key;

    /**
     *
     * 参数名称
     *
     */
    @ApiModelProperty(name = "name", value = "参数名称")
    private String name;


    @ApiModelProperty(name = "attributes", value = "参数列表")
    private List<ProductAttributeVO> attributes;


}
