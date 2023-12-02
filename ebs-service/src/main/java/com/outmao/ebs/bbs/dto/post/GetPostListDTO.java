package com.outmao.ebs.bbs.dto.post;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetPostListDTO", description = "获取贴子列表参数")
@Data
public class GetPostListDTO {

    @ApiModelProperty(name = "userId", value = "发表用户ID")
    private Long userId;

    @ApiModelProperty(name = "subjectId", value = "主题ID")
    private Long subjectId;

    @ApiModelProperty(name = "itemId", value = "绑定业务对象ID")
    private Long itemId;

    @ApiModelProperty(name = "itemType", value = "绑定业务对象类型")
    private String itemType;

    @ApiModelProperty(name = "type", value = "0--论坛 1--朋友圈 2--商品评价")
    private Integer type;

}
