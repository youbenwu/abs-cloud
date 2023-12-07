package com.outmao.ebs.portal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetAdvertChannelListDTO {


    private Long orgId;

    @ApiModelProperty(name = "type", value = "广告类型 0--系统广告 1--企业广告 2--个人广告")
    private Integer type;



}
