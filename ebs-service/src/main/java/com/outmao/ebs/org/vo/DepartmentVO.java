package com.outmao.ebs.org.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DepartmentVO {

    private Long id;
    private Long orgId;
    private Long parentId;
    private List<DepartmentVO> children;
    private int level;
    private boolean leaf;
    private int sort;
    private String name; //部门名称
    private String description;//部门描述
    private int members;
    private Date createTime;
    private Date updateTime;

}
