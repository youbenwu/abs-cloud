package com.outmao.ebs.hotel.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelDevice")
public class HotelDevice  implements Serializable {

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
     * 迁眼PAD应用--会同时创建一个虚拟的用户，代表房客用户
     *
     */
    private Long userId;

    /**
     *
     * 操作激活用户的ID
     *
     */
    private Long actUserId;


    /**
     * 0--未激活
     * 1--已激活
     */
    private int status;


    /**
     * 酒店对应组织ID
     */
    private Long orgId;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 绑定房间号
     */
    private String roomNo;

    /**
     * 设备投放省份
     */
    private String province;

    /**
     * 设备投放城市
     */
    private String city;

    /**
     *
     * 设备购买信息
     *
     */
    @Embedded
    private HotelDeviceBuy buy;

    /**
     *
     * 租赁信息
     *
     */
    @Embedded
    private HotelDeviceLease lease;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    /**
     * 设备号
     */
    @Column(unique = true)
    private String deviceNo;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 设备系统
     */
    private String os;

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
