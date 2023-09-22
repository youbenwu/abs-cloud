package com.outmao.ebs.sys.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetSysMenuDTO {

    private Long sysId;

    private List<Long> menus;

}
