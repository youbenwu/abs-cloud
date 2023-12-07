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
public class HotelDeviceLeaseOrderVO implements Serializable {


    /**
     *
     * 自动编号
     *
     */
    private Long id;

    /**
     *
     * 租赁用户ID
     *
     */
    private Long userId;

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

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;



}
