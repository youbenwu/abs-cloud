package com.outmao.ebs.hotel.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁订单
 *
 */
@Data
public class MinHotelDeviceLeaseOrderVO implements Serializable {

    /**
     *
     * 自动编号
     *
     */
    private Long id;

    private Long userId;

    /**
     *
     * 租赁设备数量
     *
     */
    private int quantity;


    /**
     *
     * 本次租赁金额
     *
     */
    private double amount;

    /**
     *
     * 租赁开始时间
     *
     */
    private Date startTime;

    /**
     *
     * 租赁结束时间
     *
     */
    private Date endTime;



}
