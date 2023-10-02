package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.RoleMenu;
import com.outmao.ebs.org.service.RoleService;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "account-org-role", tags = "后台-组织-角色")
@RestController
@RequestMapping("/api/admin/org/role")
public class RoleAdminAction {


    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasPermission(#request.orgId,'/org/role','save')")
    @ApiOperation(value = "保存角色", notes = "保存角色")
    @PostMapping("/save")
    public void saveRole(RoleDTO request){
        roleService.saveRole(request);
    }

    @PreAuthorize("hasPermission('/org/role','delete')")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/delete")
    public void deleteRole(DeleteRoleDTO request){
        roleService.deleteRole(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/role','read')")
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @PostMapping("/list")
    public List<RoleVO> getRoleVOList(GetRoleListDTO request){
        return roleService.getRoleVOList(request);
    }



    @PreAuthorize("hasPermission('/org/role/permission','save')")
    @ApiOperation(value = "设置角色权限列表", notes = "设置角色权限列表")
    @PostMapping("/permission/set")
    public void setRolePermission(@RequestBody SetRolePermissionDTO request){
        roleService.setRolePermission(request);
    }


    @PreAuthorize("hasPermission('/org/role/permission','read')")
    @ApiOperation(value = "获取角色权限列表", notes = "获取角色权限列表")
    @PostMapping("/permission/list")
    public List<RolePermissionVO> getRolePermissionVOList(GetRolePermissionListDTO request){
        return roleService.getRolePermissionVOList(request);
    }

    @PreAuthorize("hasPermission('/org/role/menu','save')")
    @ApiOperation(value = "设置角色菜单列表", notes = "设置角色菜单列表")
    @PostMapping("/menu/set")
    public void setRoleMenu(@RequestBody SetRoleMenuDTO request) {
        roleService.setRoleMenu(request);
    }

    @PreAuthorize("hasPermission('/org/role/menu','read')")
    @ApiOperation(value = "获取角色菜单列表", notes = "获取角色菜单列表")
    @PostMapping("/menu/list")
    public List<RoleMenu> getRoleMenuListByRoleId(Long roleId) {
        return roleService.getRoleMenuListByRoleId(roleId);
    }




}
