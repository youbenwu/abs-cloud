package com.outmao.ebs.portal.vo;

import com.outmao.ebs.common.vo.BindingItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecommendVO<T> {

    private Long id;

    @ApiModelProperty(name = "sort", value = "排序")
    private int sort;

    @ApiModelProperty(name = "type", value = "推荐类型 0--首页 1--banner")
    private int type;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "item", value = "绑定内容")
    private BindingItem item;

    @ApiModelProperty(name = "title", value = "名称")
    private String title;

    @ApiModelProperty(name = "image", value = "图片")
    private String image;

    private T data;

}
