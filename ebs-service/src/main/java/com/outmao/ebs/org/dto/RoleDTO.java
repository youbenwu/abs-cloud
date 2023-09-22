package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 角色值
     */
    private String value;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 排序 从0开始
     */
    private int sort;


}
