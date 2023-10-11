package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.dto.PermissionDTO;
import com.outmao.ebs.org.entity.Permission;
import com.outmao.ebs.org.service.PermissionService;
import com.outmao.ebs.org.vo.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AccessPermissionGroup(title="组织管理",url="/org",name="",children = {
        @AccessPermissionParent(title = "权限管理",url = "/org/permission",name = "",children = {
                @AccessPermission(title = "保存权限",url = "/org/permission",name = "save"),
                @AccessPermission(title = "删除权限",url = "/org/permission",name = "delete"),
                @AccessPermission(title = "读取权限",url = "/org/permission",name = "read"),
        }),
})



@Api(value = "account-org-permission", tags = "后台-组织-权限")
@RestController
@RequestMapping("/api/admin/org/permission")
public class PermissionAdminAction {

	@Autowired
    private PermissionService permissionService;


    @PreAuthorize("hasPermission('/org/permission','save')")
    @ApiOperation(value = "保存权限", notes = "保存权限")
    @PostMapping("/save")
    public Permission savePermission(PermissionDTO request){
        return permissionService.savePermission(request);
    }

    @PreAuthorize("hasPermission('/org/permission','delete')")
    @ApiOperation(value = "删除权限", notes = "删除权限")
    @PostMapping("/delete")
    public void deletePermissionById(Long id){
        permissionService.deletePermissionById(id);
    }

    @PreAuthorize("hasPermission('/org/permission','read')")
    @ApiOperation(value = "获取权限列表", notes = "获取权限列表")
    @PostMapping("/list")
    public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request){
        return permissionService.getPermissionVOList(request);
    }

    @PreAuthorize("hasPermission('/org/permission','read')")
    @ApiOperation(value = "获取系统权限列表", notes = "获取系统权限列表")
    @PostMapping("/listBySys")
    public List<PermissionVO> getPermissionVOListBySysId(Long sysId){
        List<PermissionVO> list= permissionService.getPermissionVOListBySysId(sysId);
        if(list.isEmpty()){
            list=permissionService.getPermissionVOList(new GetPermissionListDTO());
        }
        return list;
    }

}
