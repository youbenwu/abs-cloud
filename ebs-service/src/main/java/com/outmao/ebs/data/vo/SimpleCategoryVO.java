package com.outmao.ebs.data.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "CategoryVO", description = "文章分类信息")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class SimpleCategoryVO {

    /**
     *
     * 分类唯一不变标识
     *
     */
    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    /**
     *
     * 分类的父分类
     *
     */
    @ApiModelProperty(name = "parentId", value = "分类的父分类ID")
    private Long parentId;


    /**
     *
     * 分类的标题
     *
     */
    @ApiModelProperty(name = "title", value = "分类的标题")
    private String title;


}
