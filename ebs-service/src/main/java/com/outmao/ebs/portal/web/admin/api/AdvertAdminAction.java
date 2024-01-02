package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.portal.vo.StatsAdvertStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AccessPermissionGroup(title="门户",url="/portal",name="",children = {
        @AccessPermissionParent(title = "广告管理",url = "/portal/advert",name = "",children = {
                @AccessPermission(title = "保存广告信息",url = "/portal/advert",name = "save"),
                @AccessPermission(title = "删除广告信息",url = "/portal/advert",name = "delete"),
                @AccessPermission(title = "读取广告信息",url = "/portal/advert",name = "read"),
                @AccessPermission(title = "设置广告状态",url = "/portal/advert",name = "status"),
        }),
        @AccessPermissionParent(title = "广告频道管理",url = "/portal/advert/channel",name = "",children = {
                @AccessPermission(title = "保存广告频道信息",url = "/portal/advert/channel",name = "save"),
                @AccessPermission(title = "删除广告频道信息",url = "/portal/advert/channel",name = "delete"),
                @AccessPermission(title = "读取广告频道信息",url = "/portal/advert/channel",name = "read"),
        }),

})


@Api(value = "admin-portal-advert", tags = "后台-门户-广告")
@RestController
@RequestMapping("/api/admin/portal/advert")
public class AdvertAdminAction {

	@Autowired
    private AdvertService advertService;


    @Autowired
	private AdvertChannelService advertChannelService;


    @PreAuthorize("hasPermission('/portal/advert','save')")
    @ApiOperation(value = "保存广告信息", notes = "保存广告信息")
    @PostMapping("/save")
    public Advert saveAdvert(@RequestBody AdvertDTO request) {
        return advertService.saveAdvert(request);
    }

    @PreAuthorize("hasPermission('/portal/advert','status')")
    @ApiOperation(value = "设置广告上下架", notes = "设置广告上下架")
    @PostMapping("/display")
    public Advert setAdvertDisplay(SetAdvertDisplayDTO request){
        return advertService.setAdvertDisplay(request);
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
    public Page<AdvertVO> getAdvertVOPage(GetAdvertListDTO request, @PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC)Pageable pageable) {
        return advertService.getAdvertVOPage(request,pageable);
    }

    @PreAuthorize("hasPermission('/portal/advert','read')")
    @ApiOperation(value = "广告按状态统计数量", notes = "广告按状态统计数量")
    @PostMapping("/stats/status")
    public List<StatsAdvertStatusVO> getStatsAdvertStatusVOList(GetAdvertListDTO request){
        return advertService.getStatsAdvertStatusVOList(request);
    }



    //

    @PreAuthorize("hasPermission('/portal/advert/channel','save')")
    @ApiOperation(value = "保存广告频道", notes = "保存广告频道")
    @PostMapping("/channel/save")
    public void saveAdvertChannel(AdvertChannelDTO request){
        advertChannelService.saveAdvertChannel(request);
    }

    @PreAuthorize("hasPermission('/portal/advert/channel','delete')")
    @ApiOperation(value = "保存广告频道", notes = "保存广告频道")
    @PostMapping("/channel/delete")
    public void deleteAdvertChannelById(Long id){
        advertChannelService.deleteAdvertChannelById(id);
    }


    @PreAuthorize("hasPermission('/portal/advert/channel','read')")
    @ApiOperation(value = "获取广告频道", notes = "获取广告频道")
    @PostMapping("/channel/get")
    public AdvertChannelVO getAdvertChannelVOById(Long id){
        return advertChannelService.getAdvertChannelVOById(id);
    }


    @PreAuthorize("hasPermission('/portal/advert/channel','read')")
    @ApiOperation(value = "获取广告频道", notes = "获取广告频道")
    @PostMapping("/channel/getByCode")
    public AdvertChannelVO getAdvertChannelVOByCode(String code){
        return advertChannelService.getAdvertChannelVOByCode(code);
    }

    @PreAuthorize("hasPermission('/portal/advert/channel','read')")
    @ApiOperation(value = "获取广告频道列表", notes = "获取广告频道列表")
    @PostMapping("/channel/page")
    public Page<AdvertChannelVO> getAdvertChannelVOPage(GetAdvertChannelListDTO request, Pageable pageable){
        return advertChannelService.getAdvertChannelVOPage(request,pageable);
    }


}
