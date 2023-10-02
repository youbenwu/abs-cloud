package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.dto.AccountRoleDTO;
import com.outmao.ebs.org.dto.GetAccountListDTO;
import com.outmao.ebs.org.dto.SetAccountRoleDTO;
import com.outmao.ebs.org.service.AccountService;
import com.outmao.ebs.org.vo.AccountVO;
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


@AccessPermissionGroup(title="组织管理",url="/org",name="",children = {

        @AccessPermissionParent(title = "账号管理",url = "/org/account",name = "",children = {
                @AccessPermission(title = "保存账号",url = "/org/account",name = "save"),
                @AccessPermission(title = "删除账号",url = "/org/account",name = "delete"),
                @AccessPermission(title = "读取账号",url = "/org/account",name = "read"),
        }),
        @AccessPermissionParent(title = "账号角色管理",url = "/org/account/role",name = "",children = {
                @AccessPermission(title = "保存账号角色",url = "/org/account/role",name = "save"),
                @AccessPermission(title = "删除账号角色",url = "/org/account/role",name = "delete"),
                @AccessPermission(title = "读取账号角色",url = "/org/account/role",name = "read"),
        }),

})


@Api(value = "account-org-account", tags = "后台-组织-账号")
@RestController
@RequestMapping("/api/admin/org/account")
public class AccountAdminAction {



    @Autowired
    private AccountService accountService;



    @PreAuthorize("hasPermission(#request.orgId,'/org/account','save')")
    @ApiOperation(value = "保存账号", notes = "保存账号")
    @PostMapping("/save")
    public void saveAccount(AccountDTO request){
        if("admin".equals(request.getName())){
            throw new BusinessException("账号名称不能是admin");
        }
        accountService.saveAccount(request);
    }

    @PreAuthorize("hasPermission(null,'/org/account','delete')")
    @ApiOperation(value = "删除账号", notes = "删除账号")
    @PostMapping("/delete")
    public void deleteAccountById(Long id){
        accountService.deleteAccountById(id);
    }


    @PreAuthorize("hasPermission(#request.orgId,'/org/account','read')")
    @ApiOperation(value = "获取账号列表", notes = "获取账号列表")
    @PostMapping("/page")
    public Page<AccountVO> getAccountVOPage(GetAccountListDTO request, Pageable pageable){
        return accountService.getAccountVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission(null,'/org/account/role','save')")
    @ApiOperation(value = "保存账号角色", notes = "保存账号角色")
    @PostMapping("/role/save")
    public void saveAccountRole(AccountRoleDTO request){
        accountService.saveAccountRole(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/account/role','delete')")
    @ApiOperation(value = "删除账号角色", notes = "删除账号角色")
    @PostMapping("/role/delete")
    public void deleteAccountRoleById(Long id){
        accountService.deleteAccountRoleById(id);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/account/role','save')")
    @ApiOperation(value = "设置账号角色", notes = "设置账号角色")
    @PostMapping("/role/set")
    public void setAccountRole(@RequestBody SetAccountRoleDTO request){
        accountService.setAccountRole(request);
    }



}
