package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 客服服务
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelWorkOrder")
public class HotelWorkOrder implements Serializable {

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
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;


    /**
     *
     * 状态
     * 0-未处理 1-已处理
     *
     */
    private int status;

    /**
     *
     * 状态备注
     *
     */
    private String statusRemark;

    /**
     *
     * 房间号
     *
     */
    private String roomNo;


    /**
     *
     * 提交用户的ID
     *
     */
    private Long userId;

    /**
     *
     * 提交用户的名称
     *
     */
    private String userName;


    /**
     *
     * 提交用户的手机号
     *
     */
    private String userPhone;

    /**
     *
     * 服务名称
     *
     */
    private String name;

    /**
     *
     * 用户提交的服务内容
     *
     */
    private String content;

    /**
     *
     * 用户提交的服务开始时间
     *
     */
    private Date startTime;


    /**
     *
     * 用户提交的服务结束时间
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
