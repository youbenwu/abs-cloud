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
     * 拥有设备数量
     *
     */
    private int quantity;


    /**
     *
     * 新增设备数量
     *
     */
    private int addQuantity;


}
