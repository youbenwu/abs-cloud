package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.common.vo.TimeSpan;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class CreateHotelDeviceLeaseOrderDTO {


    private Long merchantId;
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
     * 订单号
     *
     */
    private String orderNo;

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
     * 本次租赁金额
     *
     */
    private double amount;

    /**
     *
     * 租赁期限
     *
     */
    private TimeSpan time;


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
