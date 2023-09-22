package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ebs_HotelDevice", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "deviceNo", "hotelId" }) })
public class HotelDevice  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public static final String APP_TYPE_QY_PAD="ROOM_PAD";


    /**
     *
     * 自动编号
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 组织ID
     */
    @Column(nullable = false)
    private Long orgId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    private Hotel hotel;


    /**
     * 房间号
     */
    private String roomNo;

    /**
     *
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;



    /**
     * 迁眼PAD应用--会同时创建一个虚拟的用户，代表房客用户
     */
    private Long userId;


    /**
     * 设备号
     */
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
     * 应用类型
     */
    private String appType;


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
