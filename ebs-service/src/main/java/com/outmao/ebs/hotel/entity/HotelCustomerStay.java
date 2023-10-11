package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 客人入住记录
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelCustomerStay")
public class HotelCustomerStay implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int STATUS_STAY_NOT_IN=0;
    public static final int STATUS_STAY_IN=1;
    public static final int STATUS_STAY_OUT=2;


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
     * 用户ID
     *
     */
    @Column(nullable = false)
    private Long userId;

    /**
     *
     * 客户ID
     *
     */
    @Column(nullable = false)
    private Long customerId;


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
     * 0-未入住 1-已入住  2-已退房
     *
     */
    private int status;

    /**
     *
     * 状态
     *
     *
     */
    private String statusRemark;

    /**
     *
     * 入住房间
     *
     */
    private String roomNo;

    /**
     *
     * 房间价格
     *
     */
    private double price;

    /**
     *
     * 入住天数
     *
     */
    private int stayDays;


    /**
     *
     * 押金
     *
     */
    private double rents;

    /**
     *
     * 消费金额
     *
     */
    private double amount;

    /**
     *
     * 开始时间
     *
     */
    private Date startTime;

    /**
     *
     * 结束时间
     *
     */
    private Date endTime;


    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 手机号
     *
     */
    private String phone;

    /**
     *
     * 身份证号码
     *
     */
    private String idNo;


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
