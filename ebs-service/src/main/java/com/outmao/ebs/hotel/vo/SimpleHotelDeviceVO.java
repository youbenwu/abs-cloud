package com.outmao.ebs.hotel.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *
 * 设备
 *
 */
@Data
public class SimpleHotelDeviceVO {


    private Long id;

    private Long hotelId;

    @ApiModelProperty(name = "roomNo", value = "房间号")
    private String roomNo;

    @ApiModelProperty(name = "userId", value = "迁眼PAD应用--会同时创建一个虚拟的用户，代表房客用户")
    private Long userId;

    @ApiModelProperty(name = "deviceNo", value = "设备号")
    private String deviceNo;

    private Long renterId;

    private int leaseStatus;


}
