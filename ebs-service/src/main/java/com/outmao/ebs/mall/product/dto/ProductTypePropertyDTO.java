package com.outmao.ebs.mall.product.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductTypePropertyDTO", description = "商品类型属性")
@Data
public class ProductTypePropertyDTO {

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
    @ApiModelProperty(name = "name", value = "属性名称")
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
     * 显示后缀
     *
     */
    @ApiModelProperty(name = "suffix", value = "显示后缀")
    private String suffix;

    /**
     *
     * 唯一属性  单选属性  复选属性
     *
     */
    @ApiModelProperty(name = "propertyType", value = "0唯一属性  1单选属性  2复选属性")
    private int propertyType;

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
     * 参数录入方式：0--手动录入 1--列表中选择
     *
     */
    @ApiModelProperty(name = "inputType", value = "参数录入方式：0--手动录入 1--列表中选择")
    private int inputType;

    /**
     *
     * 是否支持手动新增
     *
     */
    @ApiModelProperty(name = "addable", value = "是否支持手动新增")
    private boolean addable;

}
