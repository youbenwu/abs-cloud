package com.outmao.ebs.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelDeviceRenterLeaseDTO extends HotelDeviceRenterDTO  {


    /**
     *
     * 在租设备数量
     *
     */
    private int quantity;

    /**
     *
     * 租赁设备消费金额
     *
     */
    private double amount;



}
