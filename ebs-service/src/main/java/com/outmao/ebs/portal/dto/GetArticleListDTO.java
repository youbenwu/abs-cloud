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

    @ApiModelProperty(name = "statusIn", value = "状态 0--正常 1--禁用 2--未审核 3--审核中 4--审核成功 5--审核失败 6--删除")
    private List<Integer> statusIn;

    @ApiModelProperty(name = "userId", value = "发表用户的ID")
    private Long userId;


}
