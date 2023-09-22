package com.outmao.ebs.user.dto;


import com.outmao.ebs.common.vo.Address;
import lombok.Data;

@Data
public class UserAddressDTO extends Address {

    Long id;
    Long userId;
    boolean def;
    String name;
    String phone;
    String phone2;
    String mark;
    String remark;

}
