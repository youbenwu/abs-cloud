package com.outmao.ebs.hotel.vo;


import com.outmao.ebs.mall.merchant.common.data.UserCommissionSetter;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 设备所有者
 * 记录购买设备的用户
 *
 */
@Data
public class MinHotelDeviceRenterVO {


    private Long id;

    private Long userId;

    /**
     *
     * 在租设备数量
     *
     */
    @ApiModelProperty(name = "quantity", value = "在租设备数量")
    private int quantity;

    /**
     *
     * 租赁设备消费总金额
     *
     */
    @ApiModelProperty(name = "amount", value = "租赁设备消费总金额")
    private double amount;


    /**
     *
     * 最后一次租赁开始时间
     *
     */
    @ApiModelProperty(name = "startTime", value = "最后一次租赁开始时间")
    private Date startTime;

    /**
     *
     * 最后一次租赁结束时间
     *
     */
    @ApiModelProperty(name = "endTime", value = "最后一次租赁结束时间")
    private Date endTime;



}

