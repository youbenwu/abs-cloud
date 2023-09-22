package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.service.AdvertService;
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


@AccessPermissionGroup(title="门户",url="/portal",name="",children = {
        @AccessPermissionParent(title = "广告管理",url = "/portal/advert",name = "",children = {
                @AccessPermission(title = "保存广告信息",url = "/portal/advert",name = "save"),
                @AccessPermission(title = "删除广告信息",url = "/portal/advert",name = "delete"),
                @AccessPermission(title = "读取广告信息",url = "/portal/advert",name = "read"),
                @AccessPermission(title = "设置广告状态",url = "/portal/advert",name = "status"),
        }),

})


@Api(value = "admin-portal-advert", tags = "后台-门户-广告")
@RestController
@RequestMapping("/api/admin/portal/advert")
public class AdvertAdminAction {

	@Autowired
    private AdvertService advertService;


    @PreAuthorize("hasPermission('/portal/advert','save')")
    @ApiOperation(value = "保存广告信息", notes = "保存广告信息")
    @PostMapping("/save")
    public Advert saveAdvert(@RequestBody AdvertDTO request) {
        return advertService.saveAdvert(request);
    }

    @PreAuthorize("hasPermission('/portal/advert','status')")
    @ApiOperation(value = "设置广告状态", notes = "设置广告状态")
    @PostMapping("/setStatus")
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        return advertService.setAdvertStatus(request);
    }

    @PreAuthorize("hasPermission('/portal/advert','delete')")
    @ApiOperation(value = "删除广告信息", notes = "删除广告信息")
    @PostMapping("/delete")
    public void deleteAdvertById(Long id) {
        advertService.deleteAdvertById(id);
    }

    @PreAuthorize("hasPermission('/portal/advert','read')")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表")
    @PostMapping("/page")
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        return advertService.getAdvertPage(request,pageable);
    }



}
