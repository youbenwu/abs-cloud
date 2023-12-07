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
@Table(name = "ebs_HotelDeviceLeaseOrderItem", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "orderId", "deviceId" }) })
public class HotelDeviceLeaseOrderItem implements Serializable {

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
     * 订单ID
     *
     */
    @Column(nullable = false)
    private Long orderId;


    /**
     *
     * 设备ID
     *
     */
    @Column(nullable = false)
    private Long deviceId;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;



}
