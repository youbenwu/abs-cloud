package com.outmao.ebs.data.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "CategoryDTO", description = "保存分类参数")
@Data
public class CategoryDTO {

    @ApiModelProperty(name = "id", value = "分类ID")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "所属组织ID")
    private Long orgId;

    @ApiModelProperty(name = "type", value = "类型")
    private String type;

    @ApiModelProperty(name = "targetId", value = "所属对象ID")
    private Long targetId;

    @ApiModelProperty(name = "parentId", value = "上级ID")
    private Long parentId;

    @ApiModelProperty(name = "status", value = "0--显示 1--隐藏")
    private int status;

    @ApiModelProperty(name = "sort", value = "分类排序")
    private int sort;

    @ApiModelProperty(name = "image", value = "分类图片")
    private String image;

    @ApiModelProperty(name = "title", value = "分类标题",required = true)
    private String title;

    @ApiModelProperty(name = "description", value = "分类描述")
    private String description;


}