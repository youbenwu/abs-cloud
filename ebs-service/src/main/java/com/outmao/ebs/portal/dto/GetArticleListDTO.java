package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel(value = "GetArticleListDTO", description = "获取文章列表参数")
@Data
public class GetArticleListDTO  {

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "categoryId", value = "分类ID")
    private Long categoryId;

    @ApiModelProperty(name = "keyword", value = "关键字")
    private String keyword;

    @ApiModelProperty(name = "status", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核失败")
    private Integer status;

    @ApiModelProperty(name = "userId", value = "发表用户的ID")
    private Long userId;

    /**
     *
     * 类型 0--文章 20--协议
     *
     */
    @ApiModelProperty(name = "type", value = "类型 0--普通文章 10--公告 20--关于")
    private Integer type;


}
