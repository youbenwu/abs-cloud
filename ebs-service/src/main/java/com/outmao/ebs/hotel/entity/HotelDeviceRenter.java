package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSaver;
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
@Table(name = "ebs_HotelDeviceRenter")
public class HotelDeviceRenter implements Serializable , UserCommissionSaver {

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
     * 佣金ID
     *
     */
    private Long commissionId;

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
     * 总租赁设备数量
     *
     */
    private int quantity;

    /**
     *
     * 租赁设备消费总金额
     *
     */
    private double amount;

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

