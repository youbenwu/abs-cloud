package com.outmao.ebs.portal.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "GetAdvertListForHotelPadDTO", description = "获取酒店设备广告列表")
@Data
public class GetAdvertListForHotelPadDTO {


    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;

    @ApiModelProperty(name = "channelCode", value = "频道CODE")
    private String channelCode;

    @ApiModelProperty(name = "size", value = "获取个数")
    private Integer size;


}
