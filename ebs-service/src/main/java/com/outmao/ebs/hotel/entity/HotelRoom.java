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

    public static final int STATUS_NOT_STAY=0;
    public static final int STATUS_IN_STAY=1;


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
     * 房间状态
     * 0-空闲 1-有客
     *
     */
    private int status;

    /**
     *
     * 房间状态备注
     *
     */
    private String statusRemark;

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
