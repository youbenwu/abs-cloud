package com.outmao.ebs.org.dto;

import lombok.Data;

@Data
public class DepartmentDTO {

    private Long id;
    private Long orgId;
    private Long parentId;
    private int sort;
    private String name;
    private String intro;

}
