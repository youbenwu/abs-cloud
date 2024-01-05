package com.outmao.ebs.hotel.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备收益记录
 *
 */
@Data
@Entity
@Table(name = "ebs_HotelDeviceIncome", uniqueConstraints = {
        @UniqueConstraint(columnNames = {  "deviceId","type","createTime" }) })
public class HotelDeviceIncome  implements Serializable {

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
     * 0--未结算
     * 1--已结算
     *
     */
    private int status;


    /**
     *
     * Order(0,"订单购买服务费"),
     * Advert(1,"广告收益");
     *
     */
    private int type;

    /**
     *
     * 设备ID
     *
     */
    @Column(nullable = false)
    private Long deviceId;

    /**
     *
     * 如果是订单，会有订单号
     *
     */
    private String orderNo;

    /**
     *
     * 交易金额
     *
     */
    private double totalAmount;


    /**
     *
     * 平台收取的佣金
     *
     */
    private double totalFee;

    /**
     *
     * 平台实际得到的佣金
     *
     */
    private double fee;

    /**
     *
     * 租客
     *
     */
    private Long renterId;

    /**
     *
     * 备注
     *
     */
    private String remark;

    /**
     *
     * 归属租客的佣金
     *
     */
    private double renterFee;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;



}
