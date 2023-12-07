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
public class HotelDeviceRenterVO implements Serializable , UserCommissionSetter {


    private Long id;

    private Long userId;

    /**
     *
     * 佣金ID
     *
     */
    private Long commissionId;
    @ApiModelProperty(name = "commission", value = "佣金信息")
    private UserCommissionVO commission;

    /**
     *
     * 姓名
     *
     */
    private String name;

    /**
     *
     * 电话
     *
     */
    private String phone;

    /**
     *
     * 在租设备数量
     *
     */
    private int quantity;

    /**
     *
     * 租赁设备消费总金额
     *
     */
    private double amount;

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

