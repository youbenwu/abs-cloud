package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetRolePermissionDTO {

    private Long roleId;

    private List<Long> permissions;

}
