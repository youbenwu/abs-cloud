package com.outmao.ebs.user.dto;

import lombok.Data;

@Data
public class SetIdCardStatusDTO {

    private Long id;
    private int status;
    private String statusRemark;

}
