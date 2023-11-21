package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetAdvertListDTO", description = "获取广告列表")
@Data
public class GetAdvertListDTO  {

    @ApiModelProperty(name = "orgId", value = "组织ID")
    private Long orgId;

    @ApiModelProperty(name = "placeId", value = "场所ID")
    private Long placeId;

    private String city;

    @ApiModelProperty(name = "channelId", value = "频道ID")
    private Long channelId;

    @ApiModelProperty(name = "channelCode", value = "频道CODE")
    private String channelCode;

    @ApiModelProperty(name = "keyword", value = "关键字，不传查全部")
    private String keyword;

    @ApiModelProperty(name = "status", value = "0--未上架 1--已上架,不传查全部")
    private Integer status;

}
