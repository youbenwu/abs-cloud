package com.outmao.ebs.hotel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 * 新增设备绑定用户
 *
 */
@NoArgsConstructor
@AllArgsConstructor
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
     * 合伙人ID
     *
     */
    private Long partnerId;

    /**
     *
     * 购买设备的金额
     *
     */
    private Double amount;


}
