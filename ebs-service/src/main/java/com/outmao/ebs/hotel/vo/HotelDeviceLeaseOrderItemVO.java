package com.outmao.ebs.hotel.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁订单
 *
 */
@Data
public class HotelDeviceLeaseOrderItemVO implements Serializable {


    /**
     *
     * 自动编号
     *
     */
    private Long id;


    /**
     *
     * 订单ID
     *
     */
    private Long orderId;


    /**
     *
     * 设备ID
     *
     */
    private Long deviceId;


    /**
     *
     * 创建时间
     *
     */
    private Date createTime;



}
