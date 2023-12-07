package com.outmao.ebs.hotel.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁订单
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelDeviceLeaseOrder")
public class HotelDeviceLeaseOrder implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     *
     * 自动编号
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     * 租赁用户ID
     *
     */
    @Column(nullable = false)
    private Long userId;

    /**
     *
     * 订单号
     *
     */
    @Column(unique = true,nullable = false)
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
