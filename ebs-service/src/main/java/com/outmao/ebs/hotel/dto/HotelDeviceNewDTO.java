package com.outmao.ebs.hotel.dto;


import lombok.Data;


/**
 *
 * 新增设备绑定用户
 *
 */
@Data
public class HotelDeviceNewDTO {


    /**
     *
     * 设备所有者用户ID
     *
     */
    private Long ownerId;

    /**
     *
     * 购买设备的金额
     *
     */
    private Double amount;


}
