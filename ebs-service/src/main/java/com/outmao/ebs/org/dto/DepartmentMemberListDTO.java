package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentMemberListDTO {


    private Long departmentId;

    private List<Long> members;


}
