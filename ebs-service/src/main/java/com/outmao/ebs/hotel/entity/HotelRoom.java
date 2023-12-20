package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 房间
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelRoom", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "roomNo", "hotelId" }) })
public class HotelRoom implements Serializable {

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
     * 组织ID
     */
    @Column(nullable = false)
    private Long orgId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId")
    private Hotel hotel;


    /**
     *
     * 房型
     *
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "typeId")
    private HotelRoomType type;


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
     * 房间状态
     * 0-空闲 1-有客 2-维修
     *
     */
    private int status;

    /**
     *
     * 房间状态备注
     *
     *
     */
    private String statusRemark;



    /**
     *
     * 房间设备状态
     * 0--无设备 1--设备投放中 2--设备已投放
     *
     */
    private int deviceStatus;

    /**
     *
     * 房间设备ID
     *
     *
     */
    private Long deviceId;


    /**
     *
     * 房间号
     *
     */
    @Column(nullable = false)
    private String roomNo;

    /**
     *
     * 房间名称
     *
     */
    private String name;

    /**
     *
     * 房间配置
     *
     */
    private String intro;

    /**
     *
     * 房间图片
     *
     */
    private String image;

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
