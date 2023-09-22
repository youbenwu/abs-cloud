package com.outmao.ebs.org.vo;


import lombok.Data;

import java.util.List;

@Data
public class MenuVO {

    private Long id;
    private int level;
    private boolean leaf;
    private Long parentId;
    private List<MenuVO> children;
    private int sort;
    private int status;
    private String name;
    private String url;
    private String icon;
    private String path;
    private String description;

}
