package com.outmao.ebs.org.dto;


import lombok.Data;

@Data
public class MenuDTO {

    private Long id;
    private Long parentId;
    private int sort;
    private int status;
    private String name;
    private String url;
    private String icon;
    private String path;
    private String description;

}
