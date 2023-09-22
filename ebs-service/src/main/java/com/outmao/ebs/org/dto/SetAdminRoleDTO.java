package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetAdminRoleDTO {

    private Long adminId;

    private List<Long> roles;

}
