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
public class HotelDeviceBuyDTO {


    /**
     *
     * 购买设备数量
     *
     */
    private int quantity;

    /**
     *
     * 购买单价
     *
     */
    private double price;


    /**
     *
     * 购买用户ID
     *
     */
    private Long userId;

    /**
     *
     * 绑定合伙人ID
     *
     */
    private Long partnerId;


}
