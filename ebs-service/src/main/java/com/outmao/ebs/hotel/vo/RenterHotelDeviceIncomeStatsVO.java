package com.outmao.ebs.hotel.vo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 单个设备租赁收益统计
 *
 */
@Data
public class RenterHotelDeviceIncomeStatsVO {

    /**
     *
     * 设备ID
     *
     */
    private Long deviceId;


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
