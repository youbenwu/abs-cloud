package com.outmao.ebs.user.dto;

import lombok.Data;

@Data
public class SetUserCertificationStatusDTO {

    private Long id;

    private int status;

    private String statusRemark;

}
