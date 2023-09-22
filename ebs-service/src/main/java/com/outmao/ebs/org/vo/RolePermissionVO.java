package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RolePermissionVO {

    private Long id;
    private Long roleId;
    private long permissionId;
    private PermissionVO permission;
    private Date createTime;

}
