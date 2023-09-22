package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 客人
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelCustomer", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "hotelId" }),
        @UniqueConstraint(columnNames = { "phone", "hotelId" }) })
public class HotelCustomer  implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int STATUS_STAY_NOT_IN=0;
    public static final int STATUS_STAY_IN=1;


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
     * 搜索关键字
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String keyword;

    /**
     *
     * 客户状态
     * 0-未入住 1-已入住
     *
     */
    private int stayStatus;



    /**
     *
     * 入住次数
     *
     */
    private int stays;


    /**
     *
     * 入住天数
     *
     */
    private int stayDays;


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
