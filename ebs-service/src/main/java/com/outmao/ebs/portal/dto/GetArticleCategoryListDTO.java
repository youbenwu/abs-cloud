package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetArticleCategoryListDTO {

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    private Integer status;

    /**
     *
     * 类型 0--文章 20--协议
     *
     */
    @ApiModelProperty(name = "type", value = "类型 0--普通文章 10--公告 20--关于")
    private Integer type;

}
