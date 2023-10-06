package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO {


    private Long id;

    /**
     *
     * 组织
     *
     */
    private Long orgId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色标题
     */
    private String title;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 排序 从0开始
     */
    private int sort;

    private List<Long> permissions;

    private List<Long> menus;

}
