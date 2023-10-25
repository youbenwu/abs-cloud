package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备所有者
 * 记录购买设备的用户
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Entity
@Table(name = "ebs_HotelDeviceOwner")
public class HotelDeviceOwner implements Serializable {

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


    @Column(nullable = false,unique = true)
    private Long userId;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 电话
     *
     */
    private String phone;


    /**
     *
     * 拥有设备数量
     *
     */
    private int quantity;


    /**
     *
     * 购买设备总金额
     *
     */
    private Double amount;


    /**
     *
     * 获得总收益
     *
     */
    private double income;


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

