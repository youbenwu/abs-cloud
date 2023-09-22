package com.outmao.ebs.org.dto;


import lombok.Data;

@Data
public class SetMemberStatusDTO {

    private Long id;
    private int status;
    private String statusRemark;

}
