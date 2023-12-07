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
     * 自动编号
     *
     */
    private Long id;

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
     * 租赁总金额
     *
     */
    private double amount;

    /**
     *
     * 0--未租赁 1--正租用 2--已过期
     *
     */
    private int status;

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
