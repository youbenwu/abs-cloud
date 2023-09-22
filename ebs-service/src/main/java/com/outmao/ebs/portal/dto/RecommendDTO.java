package com.outmao.ebs.portal.dto;


import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class RecommendDTO {


    @ApiModelProperty(name = "id", value = "ID")
    private Long id;

    @ApiModelProperty(name = "sort", value = "排序")
    private int sort;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "type", value = "推荐类型 0--首页 1--banner")
    private int type;

    @ApiModelProperty(name = "item", value = "绑定内容")
    @Embedded
    private BindingItem item;

    @ApiModelProperty(name = "title", value = "名称")
    private String title;

    @ApiModelProperty(name = "image", value = "图片")
    private String image;


}
