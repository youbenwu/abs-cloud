package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SetRoleMenuDTO {

    private Long roleId;

    private List<Long> menus;

}
