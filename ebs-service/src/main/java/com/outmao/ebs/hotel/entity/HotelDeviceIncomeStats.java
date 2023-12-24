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
@Table(name = "ebs_HotelDeviceIncomeStats")
public class HotelDeviceIncomeStats implements Serializable {

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


    /**
     *
     * 交易金额
     *
     */
    private double totalAmount;


    /**
     *
     * 平台收取的佣金
     *
     */
    private double totalFee;

    /**
     *
     * 平台实际得到的佣金
     *
     */
    private double fee;

    /**
     *
     * 归属租客的佣金
     *
     */
    private double renterFee;

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
