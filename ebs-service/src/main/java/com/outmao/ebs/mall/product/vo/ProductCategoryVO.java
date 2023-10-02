package com.outmao.ebs.mall.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@ApiModel(value = "ProductCategoryVO", description = "商品分类信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ProductCategoryVO {

    /**
     *
     * 分类唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;


    @ApiModelProperty(name = "typeId", value = "商品类型ID")
    private Long typeId;

    /**
     *
     * 分类的父分类
     *
     */
    @ApiModelProperty(name = "parentId", value = "分类的父分类ID")
    private Long parentId;

    /**
     *
     * 分类的子分类
     *
     */
    @ApiModelProperty(name = "children", value = "分类的子分类")
    private List<ProductCategoryVO> children;

    /**
     * 多级分类中所处的级别，级别从0开始
     *
     */
    @ApiModelProperty(name = "level", value = "多级分类中所处的级别，级别从0开始")
    private int level;

    /**
     * 多级分类中是否是叶子节点的标识
     *
     */
    @ApiModelProperty(name = "leaf", value = "多级分类中是否是叶子节点的标识")
    private boolean leaf;

    /**
     * 排序序号，从0形始
     *
     */
    @ApiModelProperty(name = "sort", value = "排序序号，从0形始")
    private int sort;

    /**
     *
     * 分类图片,分类的图片地址
     *
     */
    @ApiModelProperty(name = "image", value = "分类图片,分类的图片地址")
    private String image;

    /**
     *
     * 分类的标题
     *
     */
    @ApiModelProperty(name = "title", value = "分类的标题")
    private String title;

    /**
     * 分类的描述
     */
    @ApiModelProperty(name = "description", value = "分类的描述")
    private String description;

    /**
     *
     * 拼音字母,分类标题的拼音字母，用于检索
     *
     */
    @ApiModelProperty(name = "letter", value = "拼音字母,分类标题的拼音字母，用于检索")
    private String letter;

    /**
     *
     * 商品类型
     *
     */
    @ApiModelProperty(name = "productType", value = "商品类型")
    private int productType;

    /**
     *
     * 创建时间
     *
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;


}
