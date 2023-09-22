package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetRoleMenuDTO {

    private Long roleId;

    private List<Long> menus;

}
