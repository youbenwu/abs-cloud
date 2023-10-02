package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Role;
import com.outmao.ebs.org.entity.RoleMenu;
import com.outmao.ebs.org.entity.RolePermission;
import com.outmao.ebs.org.service.RoleService;
import com.outmao.ebs.org.vo.RoleMenuVO;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleDomain roleDomain;

    @Override
    public Role saveRole(RoleDTO request) {
        return roleDomain.saveRole(request);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleDomain.deleteRoleById(id);
    }

    @Override
    public RoleVO getRoleVOById(Long id) {
        return roleDomain.getRoleVOById(id);
    }

    @Override
    public List<RoleVO> getRoleVOList(GetRoleListDTO request) {
        return roleDomain.getRoleVOList(request);
    }

    @Override
    public RolePermission saveRolePermission(RolePermissionDTO request) {
        return roleDomain.saveRolePermission(request);
    }

    @Override
    public List<RolePermission> setRolePermission(SetRolePermissionDTO request) {
        return roleDomain.setRolePermission(request);
    }

    @Override
    public void deleteRolePermissionById(Long id) {
        roleDomain.deleteRolePermissionById(id);
    }

    @Override
    public List<RolePermissionVO> getRolePermissionVOList(GetRolePermissionListDTO request) {
        return roleDomain.getRolePermissionVOList(request);
    }

    @Override
    public RoleMenu saveRoleMenu(RoleMenuDTO request) {
        return roleDomain.saveRoleMenu(request);
    }

    @Override
    public List<RoleMenu> setRoleMenu(SetRoleMenuDTO request) {
        return roleDomain.setRoleMenu(request);
    }

    @Override
    public void deleteRoleMenuById(Long id) {
        roleDomain.deleteRoleMenuById(id);
    }

    @Override
    public List<RoleMenuVO> getRoleMenuVOList(GetRoleMenuListDTO request) {
        return roleDomain.getRoleMenuVOList(request);
    }
}
