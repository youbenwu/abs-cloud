package com.outmao.ebs.user.dto;


import com.outmao.ebs.common.vo.Address;
import lombok.Data;


@Data
public class UserLocationDTO extends Address {

    private Long userId;

}
