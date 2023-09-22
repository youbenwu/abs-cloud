package com.outmao.ebs.org.web.api;


import com.outmao.ebs.org.dto.GetPermissionListDTO;
import com.outmao.ebs.org.service.PermissionService;
import com.outmao.ebs.org.vo.PermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "org-permission", tags = "组织-权限")
@RestController
@RequestMapping("/api/org/permission")
public class PermissionAction {

	@Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "获取权限列表", notes = "获取权限列表")
    @PostMapping("/list")
    public List<PermissionVO> getPermissionVOList(GetPermissionListDTO request){
        return permissionService.getPermissionVOList(request);
    }


}
