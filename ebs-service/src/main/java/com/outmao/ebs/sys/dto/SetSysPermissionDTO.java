package com.outmao.ebs.sys.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetSysPermissionDTO {

    private Long sysId;

    private List<Long> permissions;
}
