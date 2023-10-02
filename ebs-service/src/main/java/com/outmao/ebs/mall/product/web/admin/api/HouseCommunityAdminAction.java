package com.outmao.ebs.mall.product.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.mall.product.dto.GetHouseCommunityListDTO;
import com.outmao.ebs.mall.product.dto.HouseCommunityDTO;
import com.outmao.ebs.mall.product.entity.HouseCommunity;
import com.outmao.ebs.mall.product.service.HouseCommunityService;
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

@AccessPermissionGroup(title="电商",url="/mall",name="",children = {
        @AccessPermissionParent(title = "房源小区管理",url = "/mall/house/community",name = "",children = {
                @AccessPermission(title = "保存房源小区信息",url = "/mall/house/community",name = "save"),
                @AccessPermission(title = "删除房源小区信息",url = "/mall/house/community",name = "delete"),
                @AccessPermission(title = "读取房源小区信息",url = "/mall/house/community",name = "read"),
        }),
})


@Api(value = "account-mall-house-community", tags = "后台-电商-房源-小区")
@RestController
@RequestMapping("/api/admin/mall/house/community")
public class HouseCommunityAdminAction {


    @Autowired
    private HouseCommunityService houseCommunityService;


    @PreAuthorize("hasPermission('/mall/house/community','save')")
    @ApiOperation(value = "保存小区信息", notes = "保存小区信息")
    @PostMapping("/save")
    public HouseCommunity saveHouseCommunity(@RequestBody HouseCommunityDTO request) {
        return houseCommunityService.saveHouseCommunity(request);
    }

    @PreAuthorize("hasPermission('/mall/house/community','delete')")
    @ApiOperation(value = "删除小区信息", notes = "删除小区信息")
    @PostMapping("/delete")
    public void deleteHouseCommunityById(Long id) {
        houseCommunityService.deleteHouseCommunityById(id);
    }

    @PreAuthorize("hasPermission('/mall/house/community','read')")
    @ApiOperation(value = "获取小区信息", notes = "获取小区信息")
    @PostMapping("/get")
    public HouseCommunity getHouseCommunityById(Long id) {
        return houseCommunityService.getHouseCommunityById(id);
    }

    @PreAuthorize("hasPermission('/mall/house/community','read')")
    @ApiOperation(value = "获取小区信息列表", notes = "获取小区信息列表")
    @PostMapping("/page")
    public Page<HouseCommunity> getHouseCommunityPage(GetHouseCommunityListDTO request, Pageable pageable) {
        return houseCommunityService.getHouseCommunityPage(request,pageable);
    }


}
