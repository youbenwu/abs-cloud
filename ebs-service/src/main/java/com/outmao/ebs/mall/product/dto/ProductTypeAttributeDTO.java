package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductTypeAttributeDTO", description = "商品类型参数")
@Data
public class ProductTypeAttributeDTO {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;

    /**
     *
     * 属性KEY
     *
     */
    @ApiModelProperty(name = "key", value = "属性KEY",required = true)
    private String key;

    /**
     *
     * 参数名称
     *
     */
    @ApiModelProperty(name = "name", value = "参数名称")
    private String name;

    /**
     *
     * 参数值 一行代表一个可选值
     *
     */
    @ApiModelProperty(name = "value", value = "参数值 一行代表一个可选值")
    private String value;

    /**
     *
     * 相同参数值的商品是否关联
     *
     */
    @ApiModelProperty(name = "assoc", value = "相同参数值的商品是否关联")
    private boolean assoc;

    /**
     *
     * 能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
     *
     */
    @ApiModelProperty(name = "searchType", value = "能否进行检索 0--不需要检索 1--关键字检索 2--范围检索")
    private int searchType;


    /**
     *
     * 参数录入方式：0--手动录入 1--单选参数 2--多选参数
     *
     */
    @ApiModelProperty(name = "inputType", value = "参数录入方式：0--手动录入 1--单选参数 2--多选参数")
    private int inputType;

}
