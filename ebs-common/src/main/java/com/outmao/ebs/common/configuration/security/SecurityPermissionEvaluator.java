package com.outmao.ebs.common.configuration.security;

import org.springframework.security.access.PermissionEvaluator;

public interface SecurityPermissionEvaluator extends PermissionEvaluator {
    /*
     *
     * 检查权限
     *
     * */
    public void hasPermission(Long orgId, Long userId);

}
