package com.outmao.ebs.hotel.vo;


import lombok.Data;

/**
 *
 * 租户租赁总收益统计
 *
 */
@Data
public class RenterTotalHotelDeviceIncomeStatsVO {


    /**
     *
     * 总收益
     *
     */
    private double amount;

    /**
     *
     * 7天收益
     *
     */
    private double amount7day;

    /**
     *
     * 当月收益
     *
     */
    private double amountMonth;


}
