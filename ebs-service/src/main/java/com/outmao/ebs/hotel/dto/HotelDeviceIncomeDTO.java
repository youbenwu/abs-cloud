package com.outmao.ebs.hotel.dto;


import lombok.Data;

import java.util.Date;


/**
 *
 * 设备收益记录
 *
 */
@Data
public class HotelDeviceIncomeDTO {


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
     * 归属租客的佣金
     *
     */
    private Long renterId;

    /**
     *
     * 归属租客的佣金
     *
     */
    private double renterFee;


    private Date time;

    private String remark;

}
