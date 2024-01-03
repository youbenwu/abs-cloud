package com.outmao.ebs.user.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;

/**
 * 用户认证信息
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class UserCertificationVO{


    private Long id;

    private Long userId;

    //认证状态
    private int status;

    //状态备注
    private String statusRemark;

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

    //时间
    private Date createTime;
    //时间
    private Date updateTime;

}
