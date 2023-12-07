package com.outmao.ebs.hotel.dto;

import com.outmao.ebs.mall.merchant.common.data.UserCommissionSaverRequest;
import lombok.Data;

@Data
public class HotelDeviceOwnerDTO implements UserCommissionSaverRequest {


    private Long merchantId;

    private Long userId;

    /**
     *
     * 合伙人ID
     *
     */
    private Long partnerId;


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
        return 2;
    }
}
