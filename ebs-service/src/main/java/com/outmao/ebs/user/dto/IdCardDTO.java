package com.outmao.ebs.user.dto;

import lombok.Data;

@Data
public class IdCardDTO {

    private Long id;

    private Long userId;
    //姓名
    private String name;
    //身份证号码
    private String idCardNo;
    //正面/反面
    private String images;

}
