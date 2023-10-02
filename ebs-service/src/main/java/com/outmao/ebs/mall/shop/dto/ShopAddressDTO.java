package com.outmao.ebs.mall.shop.dto;


import com.outmao.ebs.common.vo.Address;
import lombok.Data;

@Data
public class ShopAddressDTO extends Address {

    Long id;
    Long shopId;
    boolean def;
    String name;
    String phone;
    String phone2;
    String mark;
    String remark;

}
