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
public class HotelDeviceDeployHotelDTO {

    @ApiModelProperty(name = "hotelId", value = "酒店ID")
    private Long hotelId;

    @ApiModelProperty(name = "rooms", value = "选中的房间号列表")
    private List<String> rooms;
    

}
