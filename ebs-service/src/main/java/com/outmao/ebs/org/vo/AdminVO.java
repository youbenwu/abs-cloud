package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AdminVO {

    private Long id;

    private Long orgId;

    private Long userId;

    private List<AdminRoleVO> roles;

    private String name;

    /**
     * 电话
     */
    private String phone;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
