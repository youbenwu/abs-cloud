package com.outmao.ebs.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁信息
 *
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Data
@Embeddable
public class HotelDeviceLease implements Serializable {

    /**
     *
     * 0--未被租赁 1--已被租赁 2--租赁已过期
     *
     */
    @Column(name = "lease_status")
    private int status;

    /**
     *
     * 当前设备租赁人用户ID
     *
     */
    private Long renterId;

    /**
     *
     * 当前绑定合伙人ID
     *
     */
    @Column(name = "lease_partner_id")
    private Long partnerId;

    /**
     *
     * 当前租赁开始时间
     *
     */
    @Column(name = "lease_start_time")
    private Date startTime;

    /**
     *
     * 当前租赁结束时间
     *
     */
    @Column(name = "lease_end_time")
    private Date endTime;

    /**
     *
     * 当前租金金额
     *
     */
    @Column(name = "lease_amount")
    private double amount;

    /**
     *
     * 租金总金额
     *
     */
    @Column(name = "lease_total_amount")
    private double totalAmount;

    /**
     *
     * 当前租赁年限
     *
     */
    @Column(name = "lease_years")
    private int years;

    /**
     *
     * 总租赁年限
     *
     */
    @Column(name = "lease_total_years")
    private int totalYears;



}
