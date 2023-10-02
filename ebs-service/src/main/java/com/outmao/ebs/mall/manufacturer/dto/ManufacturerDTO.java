package com.outmao.ebs.mall.manufacturer.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "ManufacturerDTO", description = "开发商")
@Data
public class ManufacturerDTO {

    @ApiModelProperty(name = "id", value = "id")
    private Long id;


    /**
     * 开发商名称
     */
    @ApiModelProperty(name = "name", value = "开发商名称")
    private String name;



}
