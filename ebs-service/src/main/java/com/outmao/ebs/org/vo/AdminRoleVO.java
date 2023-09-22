package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AdminRoleVO {

    private Long id;

    private Long adminId;

    private Long roleId;

    private RoleVO role;

    /**
     * 创建时间
     */
    private Date createTime;


}
