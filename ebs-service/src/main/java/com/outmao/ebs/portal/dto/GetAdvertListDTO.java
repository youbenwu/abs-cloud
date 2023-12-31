package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetAdvertListDTO", description = "获取广告列表")
@Data
public class GetAdvertListDTO  {


    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "placeId", value = "场所ID")
    private Long placeId;

    @ApiModelProperty(name = "city", value = "城市")
    private String city;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "keyword", value = "关键字，不传查全部")
    private String keyword;

    @ApiModelProperty(name = "status", value = "状态 0--正常 2--未审核 3--审核中 4--审核失败 7--过期")
    private Integer status;

    @ApiModelProperty(name = "display", value = "是否上架")
    private Boolean display;

    @ApiModelProperty(name = "see", value = "是否在显示期限内")
    private Boolean see;

    private Integer type;

    //private Boolean random;


}
