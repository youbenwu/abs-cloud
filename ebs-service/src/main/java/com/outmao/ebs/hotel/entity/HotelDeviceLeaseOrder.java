package com.outmao.ebs.hotel.entity;


import com.outmao.ebs.common.vo.TimeSpan;
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
     * 0--未托管 1--已托管 2--已发货 3--部份激活 4--已激活
     *
     */
    private int status;

    /**
     *
     * 已经激活数量
     *
     */
    private int activeQuantity;

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
     * 租赁每台设备单价
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
     * 激活完成开始计算
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
