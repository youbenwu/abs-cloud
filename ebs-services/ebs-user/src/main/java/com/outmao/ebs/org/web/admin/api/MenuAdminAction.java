package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.MenuDTO;
import com.outmao.ebs.org.service.MenuService;
import com.outmao.ebs.org.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AccessPermissionGroup(title="组织管理",url="/org",name="",children = {
        @AccessPermissionParent(title = "菜单管理",url = "/org/menu",name = "",children = {
                @AccessPermission(title = "保存菜单",url = "/org/menu",name = "save"),
                @AccessPermission(title = "删除菜单",url = "/org/menu",name = "delete"),
                @AccessPermission(title = "读取菜单",url = "/org/menu",name = "read"),
        }),
})

@Api(value = "admin-org-menu", tags = "后台-组织-菜单")
@RestController
@RequestMapping("/api/admin/org/menu")
public class MenuAdminAction {

	@Autowired
    private MenuService menuService;


    @PreAuthorize("hasPermission('/org/menu','save')")
    @ApiOperation(value = "保存菜单", notes = "保存菜单")
    @PostMapping("/save")
    public void saveMenu(MenuDTO request){
        menuService.saveMenu(request);
    }

    @PreAuthorize("hasPermission('/org/menu','delete')")
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/delete")
    public void deleteMenuById(Long id){
        menuService.deleteMenuById(id);
    }

    @PreAuthorize("hasPermission('/org/menu','read')")
    @ApiOperation(value = "菜单列表", notes = "菜单列表")
    @PostMapping("/list")
    public List<MenuVO> getMenuVOList(){
        return menuService.getMenuVOList();
    }


    @PreAuthorize("hasPermission('/org/menu','read')")
    @ApiOperation(value = "系统菜单列表", notes = "系统菜单列表")
    @PostMapping("/listBySys")
    public List<MenuVO> getMenuVOListBySysId(Long sysId){
        return menuService.getMenuVOListBySysId(sysId);
    }


}
