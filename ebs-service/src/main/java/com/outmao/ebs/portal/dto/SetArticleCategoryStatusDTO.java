package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "SetArticleCategoryStatusDTO", description = "设置文章状态")
@Data
public class SetArticleCategoryStatusDTO {

    @ApiModelProperty(name = "id", value = "分类ID")
    private Long id;


    @ApiModelProperty(name = "status", value = "0--显示 1--隐藏")
    private int status;




}
