package com.outmao.ebs.hotel.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁记录
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelDeviceLeaseRecord", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "deviceId", "userId" }) })
public class HotelDeviceLeaseRecord implements Serializable {

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
    @Column(nullable = false)
    private Long deviceId;

    /**
     *
     * 租赁用户ID
     *
     */
    @Column(nullable = false)
    private Long userId;

    /**
     *
     * 绑定合伙人ID
     *
     */
    private Long partnerId;

    /**
     *
     * 租赁总金额
     *
     */
    private double amount;

    /**
     *
     * 0--未租赁 1--正租用 2--已过期
     *
     */
    private int status;

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
