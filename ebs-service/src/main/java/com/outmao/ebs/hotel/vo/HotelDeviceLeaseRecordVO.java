package com.outmao.ebs.hotel.vo;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁记录
 *
 */
@Data
public class HotelDeviceLeaseRecordVO implements Serializable {


    /**
     *
     * 自动编号
     *
     */
    private Long id;

    /**
     *
     * 0--未租赁 1--正租用 2--已过期
     *
     */
    private int status;

    /**
     *
     * 设备ID
     *
     */
    private Long deviceId;

    /**
     *
     * 租赁用户ID
     *
     */
    private Long userId;

    /**
     *
     * 绑定合伙人ID
     *
     */
    private Long partnerId;

    /**
     *
     * 当前租金金额
     *
     */
    private double amount;

    /**
     *
     * 租金总金额
     *
     */
    private double totalAmount;

    /**
     *
     * 当前租赁年限
     *
     */
    private int years;

    /**
     *
     * 总租赁年限
     *
     */
    private int totalYears;


    /**
     *
     * 当前租赁开始时间
     *
     */
    private Date startTime;

    /**
     *
     * 当前租赁结束时间
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
