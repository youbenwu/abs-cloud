package com.outmao.ebs.mall.product.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "ProductTypeVO", description = "商品类型信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductTypeVO {

    /**
     * 唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;


    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "type", value = "类型")
    private int type;

    /**
     *
     * 类型名称
     *
     */
    @ApiModelProperty(name = "name", value = "类型名称")
    private String name;

    /**
     *
     * 类型描述
     *
     */
    @ApiModelProperty(name = "description", value = "类型描述")
    private String description;


    @ApiModelProperty(name = "attributes", value = "参数列表")
    private List<ProductTypeAttributeGroupVO> attributes;

    @ApiModelProperty(name = "propertys", value = "属性列表")
    private List<ProductTypePropertyVO> propertys;


    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;


    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;


    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
