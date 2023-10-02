package com.outmao.ebs.mall.manufacturer.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.manufacturer.dto.CounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.GetCounselorDTO;
import com.outmao.ebs.mall.manufacturer.dto.ManufacturerDTO;
import com.outmao.ebs.mall.manufacturer.entity.Counselor;
import com.outmao.ebs.mall.manufacturer.service.ManufacturerService;
import com.outmao.ebs.mall.manufacturer.vo.CounselorVO;
import com.outmao.ebs.mall.manufacturer.vo.ManufacturerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "开发商管理",url = "/mall/manufacturer",name = "",children = {
                @AccessPermission(title = "保存开发商信息",url = "/mall/manufacturer",name = "save"),
                @AccessPermission(title = "删除开发商信息",url = "/mall/manufacturer",name = "delete"),
                @AccessPermission(title = "读取开发商信息",url = "/mall/manufacturer",name = "read"),
        }),
        @AccessPermissionParent(title = "置业顾问管理",url = "/mall/manufacturer/counselor",name = "",children = {
                @AccessPermission(title = "保存置业顾问信息",url = "/mall/manufacturer/counselor",name = "save"),
                @AccessPermission(title = "删除置业顾问信息",url = "/mall/manufacturer/counselor",name = "delete"),
                @AccessPermission(title = "读取置业顾问信息",url = "/mall/manufacturer/counselor",name = "read"),
        }),
})



@Api(value = "account-mall-manufacturer", tags = "后台-电商-开发商")
@RestController
@RequestMapping("/api/admin/mall/manufacturer")
public class ManufacturerAdminAction {


    @Autowired
    private ManufacturerService manufacturerService;



    @PreAuthorize("hasPermission('/mall/manufacturer','save')")
    @ApiOperation(value = "保存开发商信息", notes = "保存开发商信息")
    @PostMapping("/save")
    public void saveManufacturer(ManufacturerDTO request){
       manufacturerService.saveManufacturer(request);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer','delete')")
    @ApiOperation(value = "删除开发商信息", notes = "删除开发商信息")
    @PostMapping("/delete")
    public void deleteManufacturerById(Long id){
        manufacturerService.deleteManufacturerById(id);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer','read')")
    @ApiOperation(value = "获取开发商信息", notes = "获取开发商信息")
    @PostMapping("/get")
    public ManufacturerVO getManufacturerVOById(Long id){
        return manufacturerService.getManufacturerVOById(id);
    }


    @PreAuthorize("hasPermission('/mall/manufacturer','read')")
    @ApiOperation(value = "获取开发商信息", notes = "获取开发商信息")
    @PostMapping("/page")
    public Page<ManufacturerVO> getManufacturerVOPage(String keyword, Pageable pageable){
        return manufacturerService.getManufacturerVOPage(keyword,pageable);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer/counselor','save')")
    @ApiOperation(value = "保存置业顾问信息", notes = "保存置业顾问信息")
    @PostMapping("/counselor/save")
    public Counselor saveCounselor(CounselorDTO request){
        return manufacturerService.saveCounselor(request);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer/counselor','delete')")
    @ApiOperation(value = "删除置业顾问信息", notes = "删除置业顾问信息")
    @PostMapping("/counselor/delete")
    public void deleteCounselorById(Long id){
        manufacturerService.deleteCounselorById(id);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer/counselor','read')")
    @ApiOperation(value = "获取置业顾问信息", notes = "获取置业顾问信息")
    @PostMapping("/counselor/get")
    public CounselorVO getCounselorVOById(Long id){
        return manufacturerService.getCounselorVOById(id);
    }

    @PreAuthorize("hasPermission('/mall/manufacturer/counselor','read')")
    @ApiOperation(value = "获取置业顾问信息", notes = "获取置业顾问信息")
    @PostMapping("/counselor/page")
    public Page<CounselorVO> getCounselorVOPage(GetCounselorDTO request, Pageable pageable){
       return  manufacturerService.getCounselorVOPage(request,pageable);
    }




}
