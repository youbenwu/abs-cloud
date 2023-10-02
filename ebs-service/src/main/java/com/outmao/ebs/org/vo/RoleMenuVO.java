package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RoleMenuVO {

    private Long id;
    private Long roleId;
    private long menuId;
    private MenuVO menu;
    private Date createTime;

}
