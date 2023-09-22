package com.outmao.ebs.data.web.admin.api;



import com.outmao.ebs.data.dto.EnterpriseDTO;
import com.outmao.ebs.data.dto.GetEnterpriseListDTO;
import com.outmao.ebs.data.entity.enterprise.Enterprise;
import com.outmao.ebs.data.service.EnterpriseService;
import com.outmao.ebs.data.vo.EnterpriseVO;
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


@AccessPermissionGroup(title="数据",url="/data",name="",children = {
        @AccessPermissionParent(title = "企业信息管理",url = "/data/enterprise",name = "",children = {
                @AccessPermission(title = "保存企业信息",url = "/data/enterprise",name = "save"),
                @AccessPermission(title = "删除企业信息",url = "/data/enterprise",name = "delete"),
                @AccessPermission(title = "读取企业信息",url = "/data/enterprise",name = "read"),
                @AccessPermission(title = "设置企业状态",url = "/data/enterprise",name = "status"),
        }),
})



@Api(value = "admin-data-enterprise", tags = "后台-数据-企业")
@RestController
@RequestMapping("/api/admin/org/enterprise")
public class EnterpriseAdminAction {

    @Autowired
    private EnterpriseService enterpriseService;


    @PreAuthorize("hasPermission('/data/enterprise','save')")
    @ApiOperation(value = "保存企业信息", notes = "保存企业信息")
    @PostMapping("/save")
    public Enterprise saveEnterprise(@RequestBody EnterpriseDTO request){
        return enterpriseService.saveEnterprise(request);
    }

    @PreAuthorize("hasPermission('/data/enterprise','status')")
    @ApiOperation(value = "设置企业状态", notes = "设置企业状态")
    @PostMapping("/setStatus")
    public Enterprise setEnterpriseStatus(Long id, int status, String statusRemark){
        return enterpriseService.setEnterpriseStatus(id,status,statusRemark);
    }

    @PreAuthorize("hasPermission('/data/enterprise','read')")
    @ApiOperation(value = "获取企业信息", notes = "获取企业信息")
    @PostMapping("/get")
    public EnterpriseVO getEnterpriseVOById(Long id){
        return enterpriseService.getEnterpriseVOById(id);
    }

    @PreAuthorize("hasPermission('/data/enterprise','read')")
    @ApiOperation(value = "获取用户企业信息", notes = "获取用户企业信息")
    @PostMapping("/list")
    public List<EnterpriseVO> getEnterpriseVOListByUserId(Long userId){
        return enterpriseService.getEnterpriseVOListByUserId(userId);
    }

    @PreAuthorize("hasPermission('/data/enterprise','read')")
    @ApiOperation(value = "获取用户企业信息列表", notes = "获取用户企业信息列表")
    @PostMapping("/page")
    public Page<EnterpriseVO> getEnterpriseVOPage(GetEnterpriseListDTO request, Pageable pageable){
        return enterpriseService.getEnterpriseVOPage(request,pageable);
    }



}
