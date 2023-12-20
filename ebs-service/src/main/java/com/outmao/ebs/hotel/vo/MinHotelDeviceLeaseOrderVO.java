package com.outmao.ebs.hotel.vo;


import com.outmao.ebs.common.vo.TimeSpan;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备租赁订单
 *
 */
@Data
public class MinHotelDeviceLeaseOrderVO implements Serializable {

    /**
     *
     * 自动编号
     *
     */
    private Long id;

    private Long userId;

    /**
     *
     * 0--未托管 1--已托管 2--已发货 3--部份激活 4--已激活
     *
     */
    @ApiModelProperty(name = "status", value = "0--未托管 1--已托管 2--已发货 3--部份激活 4--已激活")
    private int status;

    /**
     *
     * 已经激活数量
     *
     */
    @ApiModelProperty(name = "activeQuantity", value = "已经激活数量")
    private int activeQuantity;

    /**
     *
     * 租赁设备数量
     *
     */
    private int quantity;


    /**
     *
     * 本次租赁金额
     *
     */
    private double amount;

    /**
     *
     * 租赁期限
     *
     */
    private TimeSpan time;

    /**
     *
     * 租赁开始时间
     *
     */
    private Date startTime;

    /**
     *
     * 租赁结束时间
     *
     */
    private Date endTime;



}
