package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO {

    private Long id;
    private Long parentId;
    private int sort;
    private int type;
    private String url;
    private String name;
    private String title;
    private String description;


}
