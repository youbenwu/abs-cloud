package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.service.AdminService;
import com.outmao.ebs.org.vo.AdminVO;
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


@Api(value = "admin-org-admin", tags = "后台-组织-管理员")
@RestController
@RequestMapping("/api/admin/org/admin")
public class AdminAdminAction {



    @Autowired
    private AdminService adminService;


    @PreAuthorize("hasPermission(#request.orgId,'/org/admin','save')")
    @ApiOperation(value = "保存管理员", notes = "保存管理员")
    @PostMapping("/save")
    public void saveAdmin(AdminDTO request){
        if("admin".equals(request.getName())){
            throw new BusinessException("管理员名称不能是admin");
        }
        adminService.saveAdmin(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/admin','delete')")
    @ApiOperation(value = "删除管理员", notes = "删除管理员")
    @PostMapping("/delete")
    public void deleteAdmin(DeleteAdminDTO request){
        adminService.deleteAdmin(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/admin','read')")
    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表")
    @PostMapping("/page")
    public Page<AdminVO> getAdminVOPage(GetAdminListDTO request, Pageable pageable){
        return adminService.getAdminVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/admin/role','save')")
    @ApiOperation(value = "保存管理员角色", notes = "保存管理员角色")
    @PostMapping("/role/save")
    public void saveAdminRole(AdminRoleDTO request){
        adminService.saveAdminRole(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/admin/role','save')")
    @ApiOperation(value = "保存管理员角色", notes = "保存管理员角色")
    @PostMapping("/role/set")
    public void setAdminRole(@RequestBody SetAdminRoleDTO request){
        adminService.setAdminRole(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/admin/role','delete')")
    @ApiOperation(value = "删除管理员角色", notes = "删除管理员角色")
    @PostMapping("/role/delete")
    public void deleteAdminRole(DeleteAdminRoleDTO request){
        adminService.deleteAdminRole(request);
    }


}
