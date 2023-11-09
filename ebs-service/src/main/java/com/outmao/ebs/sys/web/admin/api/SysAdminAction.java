package com.outmao.ebs.sys.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.sys.dto.SetSysMenuDTO;
import com.outmao.ebs.sys.dto.SetSysPermissionDTO;
import com.outmao.ebs.sys.dto.SysDTO;
import com.outmao.ebs.sys.entity.Sys;
import com.outmao.ebs.sys.entity.SysMenu;
import com.outmao.ebs.sys.entity.SysPermission;
import com.outmao.ebs.sys.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AccessPermissionGroup(title="系统管理",url="/sys",name="",children = {
        @AccessPermissionParent(title = "系统信息管理",url = "/sys/sys",name = "",children = {
                @AccessPermission(title = "保存系统信息",url = "/sys/sys",name = "save"),
                @AccessPermission(title = "删除系统信息",url = "/sys/sys",name = "delete"),
                @AccessPermission(title = "读取系统信息",url = "/sys/sys",name = "read"),
        }),
})

@Api(value = "account-sys", tags = "后台-系统")
@RestController
@RequestMapping("/api/admin/sys")
public class SysAdminAction {

	@Autowired
    private SysService sysService;


    @PreAuthorize("hasPermission('/sys/sys','save')")
    @ApiOperation(value = "保存系统信息", notes = "保存系统信息")
    @PostMapping("/save")
    public Sys saveSys(SysDTO request){
        return sysService.saveSys(request);
    }

    @PreAuthorize("hasPermission('/sys/sys','delete')")
    @ApiOperation(value = "删除系统信息", notes = "删除系统信息")
    @PostMapping("/delete")
    public void deleteSysById(Long id) {
        sysService.deleteSysById(id);
    }

    @PreAuthorize("hasPermission('/sys/sys','read')")
    @ApiOperation(value = "获取根系统信息", notes = "获取根系统信息")
    @PostMapping("/get")
    public Sys getSysById(Long id) {
        return sysService.getSysById(id);
    }


    @PreAuthorize("hasPermission('/sys/sys','read')")
    @ApiOperation(value = "获取系统信息", notes = "获取系统信息")
    @PostMapping("/page")
    public Page<Sys> getSysPage(Pageable pageable) {
        return sysService.getSysPage(pageable);
    }


    @PreAuthorize("hasPermission('/sys/sys','save')")
    @ApiOperation(value = "设置系统菜单列表", notes = "设置系统菜单列表")
    @PostMapping("/menu/set")
    public List<SysMenu> setSysMenu(@RequestBody SetSysMenuDTO request) {
        return sysService.setSysMenu(request);
    }

    @PreAuthorize("hasPermission('/sys/sys','read')")
    @ApiOperation(value = "获取系统菜单列表", notes = "获取系统菜单列表")
    @PostMapping("/menu/list")
    public List<SysMenu> getSysMenuListBySysId(Long sysId) {
        return sysService.getSysMenuListBySysId(sysId);
    }

    @PreAuthorize("hasPermission('/sys/sys','save')")
    @ApiOperation(value = "设置系统权限列表", notes = "设置系统权限列表")
    @PostMapping("/permission/set")
    public List<SysPermission> setSysPermission(@RequestBody SetSysPermissionDTO request) {
        return sysService.setSysPermission(request);
    }

    @PreAuthorize("hasPermission('/sys/sys','read')")
    @ApiOperation(value = "获取系统权限列表", notes = "获取系统权限列表")
    @PostMapping("/permission/list")
    public List<SysPermission> getSysPermissionListBySysId(Long sysId) {
        return sysService.getSysPermissionListBySysId(sysId);
    }


}
