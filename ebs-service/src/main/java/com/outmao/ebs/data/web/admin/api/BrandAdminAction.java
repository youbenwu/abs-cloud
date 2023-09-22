package com.outmao.ebs.data.web.admin.api;


import com.outmao.ebs.data.dto.BrandDTO;
import com.outmao.ebs.data.dto.GetBrandListDTO;
import com.outmao.ebs.data.entity.Brand;
import com.outmao.ebs.data.service.BrandService;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
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


@AccessPermissionGroup(title="数据",url="/data",name="",children = {
        @AccessPermissionParent(title = "品牌信息管理",url = "/data/brand",name = "",children = {
                @AccessPermission(title = "保存品牌信息",url = "/data/brand",name = "save"),
                @AccessPermission(title = "删除品牌信息",url = "/data/brand",name = "delete"),
                @AccessPermission(title = "读取品牌信息",url = "/data/brand",name = "read"),
                @AccessPermission(title = "设置品牌状态",url = "/data/brand",name = "status"),
        }),
})


@Api(value = "admin-data-brand", tags = "后台-数据-品牌")
@RestController
@RequestMapping("/api/admin/data/brand")
public class BrandAdminAction {

    @Autowired
    private BrandService brandService;


    @PreAuthorize("hasPermission('/data/brand','save')")
    @ApiOperation(value = "保存品牌信息", notes = "保存品牌信息")
    @PostMapping("/save")
    public Brand saveBrand(BrandDTO request){
        return brandService.saveBrand(request);
    }


    @PreAuthorize("hasPermission('/data/brand','delete')")
    @ApiOperation(value = "删除品牌信息", notes = "删除品牌信息")
    @PostMapping("/delete")
    public void deleteBrandById(Long id) {
        brandService.deleteBrandById(id);
    }

    @PreAuthorize("hasPermission('/data/brand','status')")
    @ApiOperation(value = "设置品牌状态", notes = "设置品牌状态")
    @PostMapping("/setStatus")
    public Brand setBrandStatus(Long id, int status, String statusRemark){
        return brandService.setBrandStatus(id,status,statusRemark);
    }

    @PreAuthorize("hasPermission('/data/brand','read')")
    @ApiOperation(value = "获取品牌信息", notes = "获取品牌信息")
    @PostMapping("/get")
    public Brand getBrandById(Long id){
        return brandService.getBrandById(id);
    }

    @PreAuthorize("hasPermission('/data/brand','read')")
    @ApiOperation(value = "获取品牌信息列表", notes = "获取品牌信息列表")
    @PostMapping("/page")
    public Page<Brand> getBrandPage(@RequestBody GetBrandListDTO request, Pageable pageable){
        return brandService.getBrandPage(request,pageable);
    }


}
