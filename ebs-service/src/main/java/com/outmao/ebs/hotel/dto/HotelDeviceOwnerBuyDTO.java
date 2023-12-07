package com.outmao.ebs.hotel.dto;

import lombok.Data;

@Data
public class HotelDeviceOwnerBuyDTO extends HotelDeviceOwnerDTO {

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
    private double price;


    /**
     *
     * 设备总价
     *
     */
    private double amount;


}
