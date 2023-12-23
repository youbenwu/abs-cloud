package com.outmao.ebs.hotel.dto;


import lombok.Data;

import java.util.Date;

/**
 *
 * 设备租赁记录
 *
 */
@Data
public class HotelDeviceLeaseRecordDTO {

    /**
     *
     * 设备ID
     *
     */
    private Long deviceId;

    /**
     *
     * 租赁用户ID
     *
     */
    private Long userId;

    /**
     *
     * 绑定合伙人ID
     *
     */
    private Long partnerId;

    /**
     *
     * 当前租金金额
     *
     */
    private double amount;

    /**
     *
     * 当前租赁年限
     *
     */
    private int years;


    /**
     *
     * 当前租赁开始时间
     *
     */
    private Date startTime;

    /**
     *
     * 当前租赁结束时间
     *
     */
    private Date endTime;



}
