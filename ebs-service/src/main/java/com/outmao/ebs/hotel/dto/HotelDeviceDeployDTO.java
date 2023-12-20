package com.outmao.ebs.hotel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 *
 * 酒店设备托管
 *
 */
@Data
public class HotelDeviceDeployDTO {

    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    private List<HotelDeviceDeployHotelDTO> hotels;

    private List<Long> devices;


}
