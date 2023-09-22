package com.outmao.ebs.message.dto;


import lombok.Data;

@Data
public class SetMessageStatusDTO {

    private Long id;

    private int status;

    private String statusRemark;

}
