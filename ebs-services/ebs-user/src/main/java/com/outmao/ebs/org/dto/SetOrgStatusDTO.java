package com.outmao.ebs.org.dto;


import lombok.Data;

@Data
public class SetOrgStatusDTO {

    private Long id;

    private int status;

    private String statusRemark;

}
