package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetRolePermissionDTO {

    private Long roleId;

    private List<Long> permissions;

}
