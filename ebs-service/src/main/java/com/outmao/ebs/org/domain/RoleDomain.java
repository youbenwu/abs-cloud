package com.outmao.ebs.org.domain;

import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Role;
import com.outmao.ebs.org.entity.RoleMenu;
import com.outmao.ebs.org.entity.RolePermission;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;

import java.util.Collection;
import java.util.List;

public interface RoleDomain {

    public Role saveRole(RoleDTO request);
    public void deleteRole(DeleteRoleDTO request);
    public List<RoleVO> getRoleVOList(GetRoleListDTO request);


    public RolePermission saveRolePermission(RolePermissionDTO request);
    public List<RolePermission> setRolePermission(SetRolePermissionDTO request);
    public void deleteRolePermission(DeleteRolePermissionDTO request);
    public List<RolePermissionVO> getRolePermissionVOList(GetRolePermissionListDTO request);

    public RoleMenu saveRoleMenu(RoleMenuDTO request);
    public List<RoleMenu> setRoleMenu(SetRoleMenuDTO request);
    public void deleteRoleMenuById(Long id);
    public List<RoleMenu> getRoleMenuListByRoleId(Long roleId);
    public List<RoleMenu> getRoleMenuListByRoleIdIn(Collection<Long> roleIdIn);


}
