package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.GetOrgListDTO;
import com.outmao.ebs.org.dto.OrgDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.OrgVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@AccessPermissionGroup(title="组织管理",url="/org",name="",children = {


        @AccessPermissionParent(title = "角色管理",url = "/org/role",name = "",children = {
                @AccessPermission(title = "保存角色",url = "/org/role",name = "save"),
                @AccessPermission(title = "删除角色",url = "/org/role",name = "delete"),
                @AccessPermission(title = "读取角色",url = "/org/role",name = "read"),
                @AccessPermission(title = "保存角色权限",url = "/org/role/permission",name = "save"),
                @AccessPermission(title = "删除角色权限",url = "/org/role/permission",name = "delete"),
                @AccessPermission(title = "读取角色权限",url = "/org/role/permission",name = "read"),
        }),

        @AccessPermissionParent(title = "角色管理",url = "/org/role",name = "",children = {
                @AccessPermission(title = "保存角色",url = "/org/role",name = "save"),
                @AccessPermission(title = "删除角色",url = "/org/role",name = "delete"),
                @AccessPermission(title = "读取角色",url = "/org/role",name = "read"),
                @AccessPermission(title = "保存角色权限",url = "/org/role/permission",name = "save"),
                @AccessPermission(title = "删除角色权限",url = "/org/role/permission",name = "delete"),
                @AccessPermission(title = "读取角色权限",url = "/org/role/permission",name = "read"),
                @AccessPermission(title = "保存角色菜单",url = "/org/role/menu",name = "save"),
                @AccessPermission(title = "删除角色菜单",url = "/org/role/menu",name = "delete"),
                @AccessPermission(title = "读取角色菜单",url = "/org/role/menu",name = "read"),
        }),


        @AccessPermissionParent(title = "成员管理",url = "/org/member",name = "",children = {
                @AccessPermission(title = "保存成员",url = "/org/member",name = "save"),
                @AccessPermission(title = "删除成员",url = "/org/member",name = "delete"),
                @AccessPermission(title = "读取成员",url = "/org/member",name = "read"),
                @AccessPermission(title = "保存成员角色",url = "/org/member/role",name = "save"),
                @AccessPermission(title = "删除成员角色",url = "/org/member/role",name = "delete"),
                @AccessPermission(title = "读取成员角色",url = "/org/member/role",name = "read"),
        }),

        @AccessPermissionParent(title = "组织信息管理",url = "/org/org",name = "",children = {
                @AccessPermission(title = "保存组织信息",url = "/org/org",name = "save"),
                @AccessPermission(title = "删除组织信息",url = "/org/org",name = "delete"),
                @AccessPermission(title = "读取组织信息",url = "/org/org",name = "read"),
        }),


        @AccessPermissionParent(title = "部门管理",url = "/org/department",name = "",children = {
                @AccessPermission(title = "保存部门",url = "/org/department",name = "save"),
                @AccessPermission(title = "删除部门",url = "/org/department",name = "delete"),
                @AccessPermission(title = "读取部门",url = "/org/department",name = "read"),
                @AccessPermission(title = "保存部门成员",url = "/org/department/member",name = "save"),
                @AccessPermission(title = "删除部门成员",url = "/org/department/member",name = "delete"),
                @AccessPermission(title = "读取部门成员",url = "/org/department/member",name = "read"),
        }),

        @AccessPermissionParent(title = "岗位管理",url = "/org/job",name = "",children = {
                @AccessPermission(title = "保存岗位",url = "/org/job",name = "save"),
                @AccessPermission(title = "删除岗位",url = "/org/job",name = "delete"),
                @AccessPermission(title = "读取岗位",url = "/org/job",name = "read"),
                @AccessPermission(title = "保存岗位成员",url = "/org/job/member",name = "save"),
                @AccessPermission(title = "删除岗位成员",url = "/org/job/member",name = "delete"),
                @AccessPermission(title = "读取岗位成员",url = "/org/job/member",name = "read"),
        }),



})


@Api(value = "account-org", tags = "后台-组织")
@RestController
@RequestMapping("/api/admin/org")
public class
OrgAdminAction {

	@Autowired
    private OrgService orgService;


    @PreAuthorize("hasPermission(#request.id,'/org/org','save')")
    @ApiOperation(value = "保存组织信息", notes = "保存组织信息")
    @PostMapping("/save")
    public Org saveOrg(@RequestBody OrgDTO request) {
        return orgService.saveOrg(request);
    }


    @PreAuthorize("hasPermission(#id,'/org/org','read')")
    @ApiOperation(value = "获取组织信息", notes = "获取组织信息")
    @PostMapping("/get")
    public OrgVO getOrgVOById(Long id) {
        return orgService.getOrgVOById(id);
    }

    @PostAuthorize("principal.hasOrg(returnObject.id)")
    @ApiOperation(value = "获取总系统组织信息", notes = "获取总系统组织信息")
    @PostMapping("/getSysOrg")
    public OrgVO getOrgVO() {
        return orgService.getOrgVOById(orgService.getOrg().getId());
    }


    @ApiOperation(value = "获取组织信息列表", notes = "获取组织信息列表")
    @PostMapping("/listByIdIn")
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn) {
        return orgService.getOrgVOListByIdIn(idIn);
    }


    @PreAuthorize("hasPermission('/org/org','read')")
    @ApiOperation(value = "获取组织信息列表", notes = "获取组织信息列表")
    @PostMapping("/page")
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable){
        return orgService.getOrgVOPage(request,pageable);
    }


}
