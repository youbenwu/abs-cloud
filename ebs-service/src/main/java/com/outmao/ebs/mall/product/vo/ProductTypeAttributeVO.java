package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "ProductTypeAttributeVO", description = "商品类型参数")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductTypeAttributeVO {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;

    @ApiModelProperty(name = "groupId", value = "分组ID")
    private Long groupId;

    /**
     *
     * 属性KEY
     *
     */
    @ApiModelProperty(name = "key", value = "属性KEY")
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
     * 显示后缀
     *
     */
    @ApiModelProperty(name = "suffix", value = "显示后缀")
    private String suffix;

    /**
     *
     * 相同参数值的商品是否关联
     *
     */
    @ApiModelProperty(name = "assoc", value = "相同参数值的商品是否关联")
    private Boolean assoc;

    /**
     *
     * 能否进行检索 0--不需要检索 1--关键字检索 2--范围检索
     *
     */
    @ApiModelProperty(name = "searchType", value = "能否进行检索 0--不需要检索 1--关键字检索 2--范围检索")
    private Integer searchType;


    /**
     *
     * 参数录入方式：0--手动录入 1--单选参数 2--多选参数
     *
     */
    @ApiModelProperty(name = "inputType", value = "参数录入方式：0--手动录入 1--单选参数 2--多选参数")
    private Integer inputType;

}
