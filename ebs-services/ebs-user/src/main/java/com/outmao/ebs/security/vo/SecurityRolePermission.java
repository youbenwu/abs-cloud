package com.outmao.ebs.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityRolePermission {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限URL
     */
    private String url;

    /**
     *
     * 权限值
     *
     */
    private String name;


}
