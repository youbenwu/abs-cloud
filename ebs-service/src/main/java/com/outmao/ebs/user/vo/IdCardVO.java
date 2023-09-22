package com.outmao.ebs.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class IdCardVO {

    private Long id;
    //用户
    private Long userId;
    //认证状态
    private int status;
    //状态备注
    private String statusRemark;
    //姓名
    private String name;
    //身份证号码
    private String idCardNo;
    //正面/反面
    private String images;
    //时间
    private Date createTime;
    //时间
    private Date updateTime;


}
