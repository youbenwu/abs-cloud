package com.outmao.ebs.hotel.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备数据统计
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelDeviceStats")
public class HotelDeviceStats implements Serializable {

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
     * 设备ID
     *
     */
    @Column(unique = true,nullable = false,updatable = false)
    private Long deviceId;


//    /**
//     *
//     * 产生的交易总金额
//     *
//     */
//    private double amount;
//
//
//    /**
//     *
//     * 平台抽取佣金总额
//     *
//     */
//    private double commissionAmount;


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
