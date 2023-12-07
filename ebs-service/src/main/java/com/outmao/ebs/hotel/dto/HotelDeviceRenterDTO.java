package com.outmao.ebs.hotel.dto;


import com.outmao.ebs.mall.merchant.common.data.UserCommissionSaverRequest;
import lombok.Data;

/**
 *
 * 设备所有者
 * 记录购买设备的用户
 *
 */
@Data
public class HotelDeviceRenterDTO implements UserCommissionSaverRequest {


    private Long merchantId;
    /**
     *
     * 租赁用户ID
     *
     */
    private Long userId;

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

    @Override
    public int getCommissionType() {
        return 3;
    }


}

