package com.outmao.ebs.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 用户认证信息
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserCertificationDTO {



    private Long userId;

    //姓名
    private String name;

    //0--身份证
    private int certificateType;

    //证件号码
    private int certificateNumber;

    //证件正面照
    private String certificateFront;

    //证件反面照
    private String certificateBack;


}
