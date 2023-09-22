package com.outmao.ebs.org.web.api;


import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.org.service.MenuService;
import com.outmao.ebs.org.vo.MenuVO;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityMember;
import com.outmao.ebs.security.vo.SecurityOrg;
import com.outmao.ebs.security.vo.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "org-menu", tags = "组织-菜单")
@RestController
@RequestMapping("/api/org/menu")
public class MenuAction {

	@Autowired
    private MenuService menuService;

    @ApiOperation(value = "菜单列表,用于后台管理界面菜单获取", notes = "菜单列表,用于后台管理界面菜单获取")
    @PostMapping("/list")
    public List<MenuVO> getMenuVOList(){
        Long sysId= RequestUtil.getHeaderLong("sysId");
        Long orgId= RequestUtil.getHeaderLong("orgId");
        SecurityUser user= SecurityUtil.currentUser();
        if(orgId==null){
            orgId=user.getOrgs().get(0).getOrgId();
            sysId=user.getOrgs().get(0).getSysId();
        }
        if(sysId==null){
            for (SecurityOrg org:user.getOrgs()){
                if(org.getOrgId().equals(orgId)){
                    sysId=org.getSysId();
                    break;
                }
            }
        }
        if(sysId==null||orgId==null)
            return null;
        if(user.getMembers()==null||user.getMembers().isEmpty())
            return null;

        Collection<Long> roles=new HashSet<>();
        for(SecurityMember m:user.getMembers()){
            if(m.getOrgId().equals(orgId)&&m.getRoles()!=null){
                roles.addAll(m.getRoles().stream().map(t->t.getId()).collect(Collectors.toList()));
            }
        }
        return menuService.getMenuVOListBySysIdAndRoleIdIn(sysId,roles);
    }



}
