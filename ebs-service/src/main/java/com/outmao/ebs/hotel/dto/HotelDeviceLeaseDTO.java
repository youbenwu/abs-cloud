package com.outmao.ebs.hotel.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HotelDeviceLeaseDTO {


    /**
     *
     * 租赁设备数量
     *
     */
    private int quantity;

    /**
     *
     * 租赁单价
     *
     */
    private double price;


    /**
     *
     * 当前设备租赁人用户ID
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
     * 租几年
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
