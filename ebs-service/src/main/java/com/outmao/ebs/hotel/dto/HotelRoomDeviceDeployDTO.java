package com.outmao.ebs.hotel.dto;


import lombok.Data;


/**
 *
 * 酒店设备托管
 *
 */
@Data
public class HotelRoomDeviceDeployDTO {


    private Long hotelId;

    private String roomNo;

    private Long deviceId;


}
