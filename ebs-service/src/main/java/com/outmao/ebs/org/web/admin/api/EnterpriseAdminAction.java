package com.outmao.ebs.org.web.admin.api;



import com.outmao.ebs.org.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.org.entity.enterprise.Enterprise;
import com.outmao.ebs.org.service.EnterpriseService;
import com.outmao.ebs.org.vo.EnterpriseVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AccessPermissionGroup(title="组织",url="/org",name="",children = {
        @AccessPermissionParent(title = "企业信息管理",url = "/org/enterprise",name = "",children = {
                @AccessPermission(title = "保存企业信息",url = "/org/enterprise",name = "save"),
                @AccessPermission(title = "删除企业信息",url = "/org/enterprise",name = "delete"),
                @AccessPermission(title = "读取企业信息",url = "/org/enterprise",name = "read"),
                @AccessPermission(title = "审核企业信息",url = "/org/enterprise",name = "status"),
        }),
})



@Api(value = "admin-org-enterprise", tags = "后台-组织-企业")
@RestController
@RequestMapping("/api/admin/org/enterprise")
public class EnterpriseAdminAction {

    @Autowired
    private EnterpriseService enterpriseService;


    @PreAuthorize("hasPermission(null,'/org/enterprise','save')")
    @ApiOperation(value = "保存企业信息", notes = "保存企业信息")
    @PostMapping("/save")
    public Enterprise saveEnterprise(@RequestBody EnterpriseDTO request){
        return enterpriseService.saveEnterprise(request);
    }

    @PreAuthorize("hasPermission(null,'/org/enterprise','status')")
    @ApiOperation(value = "设置企业状态", notes = "设置企业状态")
    @PostMapping("/setStatus")
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark){
        return enterpriseService.setEnterpriseStatus(id,status,statusRemark);
    }

    @PreAuthorize("hasPermission(null,'/org/enterprise','read')")
    @ApiOperation(value = "获取企业信息", notes = "获取企业信息")
    @PostMapping("/get")
    public EnterpriseVO getEnterpriseVOById(Long id){
        return enterpriseService.getEnterpriseVOById(id);
    }

    @PreAuthorize("hasPermission(null,'/org/enterprise','read')")
    @ApiOperation(value = "获取用户企业信息", notes = "获取用户企业信息")
    @PostMapping("/list")
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId){
        return enterpriseService.getEnterpriseVOListByUserId(userId);
    }

    @PreAuthorize("hasPermission(null,'/org/enterprise','read')")
    @ApiOperation(value = "获取用户企业信息列表", notes = "获取用户企业信息列表")
    @PostMapping("/page")
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable){
        return enterpriseService.getEnterpriseVOPage(request,pageable);
    }



}
