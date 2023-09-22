package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RoleVO {
    private Long id;
    private Long orgId;
    private int sort;
    private String name;
    private String value;
    private String description;
    private List<RolePermissionVO> permissions;
    private Date createTime;
    private Date updateTime;


}
