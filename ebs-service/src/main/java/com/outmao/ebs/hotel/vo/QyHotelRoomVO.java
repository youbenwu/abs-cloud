package com.outmao.ebs.hotel.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class QyHotelRoomVO extends HotelRoomVO{

    /**
     *
     * 房间设备状态
     * 0--无设备 1--设备投放中 2--设备已投放
     *
     */
    @ApiModelProperty(name = "deviceStatus", value = "房间设备状态 0--无设备 1--设备投放中 2--设备已投放")
    private int deviceStatus;


    private SimpleHotelDeviceVO device;


}
