package com.outmao.ebs.data.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "SetCategoryStatusDTO", description = "设置状态")
@Data
public class SetCategoryStatusDTO {

    @ApiModelProperty(name = "id", value = "分类ID")
    private Long id;

    @ApiModelProperty(name = "status", value = "0--显示 1--隐藏")
    private int status;




}
