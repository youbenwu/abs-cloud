package com.outmao.ebs.mall.order.dto;

import lombok.Data;

@Data
public class LdAddressDTO {

    /**
     *
     * 联系人姓名
     *
     */
    private String name;

    /**
     *
     *
     * 联系人电话号码
     *
     */
    private String phone;

    /**
     *
     *
     * 收货地址
     *
     */
    private String address;

    /**
     *
     *
     * 物流单号
     *
     */
    private String expressNo;

}
