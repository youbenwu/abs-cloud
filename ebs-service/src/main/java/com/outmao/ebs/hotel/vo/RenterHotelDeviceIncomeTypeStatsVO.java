package com.outmao.ebs.hotel.vo;


import lombok.Data;

/**
 *
 * 租户租赁总收益按类型统计
 *
 */
@Data
public class RenterHotelDeviceIncomeTypeStatsVO {


    /**
     *
     * 收益
     *
     */
    private double amount;

    private int type;

    private String typeName;


}
