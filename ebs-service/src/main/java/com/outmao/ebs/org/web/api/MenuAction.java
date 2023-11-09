package com.outmao.ebs.org.web.api;


import com.outmao.ebs.org.service.MenuService;
import com.outmao.ebs.org.vo.MenuVO;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<MenuVO> getMenuVOList(@RequestHeader Long sysId,@RequestHeader Long orgId){
        SecurityUser user= SecurityUtil.currentUser();
        List<Long> roles=new ArrayList<>();
        user.getMembers().stream().filter(m->m.getOrgId().equals(orgId)).forEach(m->{
            roles.addAll(m.getRoles().stream().map(t->t.getId()).collect(Collectors.toList()));
        });
        return menuService.getMenuVOListBySysIdAndRoleIdIn(sysId,roles);
    }



}
