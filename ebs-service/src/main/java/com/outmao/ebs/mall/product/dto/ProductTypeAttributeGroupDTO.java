package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@ApiModel(value = "ProductTypeAttributeGroupDTO", description = "商品类型参数分组")
@Data
public class ProductTypeAttributeGroupDTO {

    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;

    /**
     *
     * 分组KEY
     *
     */
    @ApiModelProperty(name = "key", value = "分组KEY",required = true)
    private String key;

    /**
     *
     * 分组名称
     *
     */
    @ApiModelProperty(name = "name", value = "分组名称")
    private String name;


    /**
     *
     * 参数列表
     *
     *
     */
    @ApiModelProperty(name = "attributes", value = "参数列表")
    private List<ProductTypeAttributeDTO> attributes;


}
