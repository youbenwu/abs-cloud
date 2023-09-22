package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetRecommendListDTO {

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "type", value = "推荐类型 0--首页 1--banner")
    private Integer type;

    @ApiModelProperty(name = "itemType", value = "绑定类型")
    private String itemType;

}
