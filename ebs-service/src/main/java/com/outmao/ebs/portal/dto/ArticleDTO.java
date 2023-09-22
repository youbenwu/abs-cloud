package com.outmao.ebs.portal.dto;


import com.outmao.ebs.data.common.data.ItemMediaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "ArticleDTO", description = "保存文章参数")
@Data
public class ArticleDTO {

    /**
     *
     * ID
     *
     */
    @ApiModelProperty(name = "id", value = "文章ID")
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "用户的ID")
    private Long userId;

    @ApiModelProperty(name = "categoryId", value = "分类ID",required = true)
    private Long categoryId;

    /**
     *
     * 文章作者
     *
     */
    @ApiModelProperty(name = "author", value = "文章作者")
    private String author;


    @ApiModelProperty(name = "medias", value = "图片列表")
    private List<ItemMediaDTO> medias;

    /**
     *
     * 文章标题
     *
     */
    @ApiModelProperty(name = "title", value = "文章标题",required = true)
    private String title;

    /**
     *
     * 文章内容详情
     *
     */
    @ApiModelProperty(name = "content", value = "文章内容详情",required = true)
    private String content;



}
