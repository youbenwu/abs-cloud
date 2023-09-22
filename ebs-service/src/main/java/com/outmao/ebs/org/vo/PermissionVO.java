package com.outmao.ebs.org.vo;


import lombok.Data;

import java.util.List;

@Data
public class PermissionVO {

    private Long id;
    private int type;
    private String title;
    private String description;
    private String url;
    private String name;
    private Long parentId;
    private List<PermissionVO> children;
    private int level;
    private boolean leaf;
    private int sort;


}
