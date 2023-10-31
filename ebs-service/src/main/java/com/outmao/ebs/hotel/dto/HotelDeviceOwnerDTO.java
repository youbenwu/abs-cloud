package com.outmao.ebs.hotel.dto;

import lombok.Data;

@Data
public class HotelDeviceOwnerDTO {

    /**
     *
     * 自动编号
     *
     */
    private Long id;



    private Long userId;

    /**
     *
     * 合伙人ID
     *
     */
    private Long partnerId;


    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 电话
     *
     */
    private String phone;


    /**
     *
     * 设备数量
     *
     */
    private int quantity;


    /**
     *
     * 设备单价
     *
     */
    private Double price;


    /**
     *
     * 设备总价
     *
     */
    private Double amount;


}
