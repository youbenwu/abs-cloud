package com.outmao.ebs.portal.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Channel;
import com.outmao.ebs.portal.service.ChannelService;
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
        @AccessPermissionParent(title = "频道管理",url = "/portal/channel",name = "",children = {
                @AccessPermission(title = "保存频道",url = "/portal/channel",name = "save"),
                @AccessPermission(title = "删除频道",url = "/portal/channel",name = "delete"),
                @AccessPermission(title = "读取频道",url = "/portal/channel",name = "read"),
        }),
})


@Api(value = "account-portal-channel", tags = "后台-门户-频道")
@RestController
@RequestMapping("/api/admin/portal/channel")
public class ChannelAdminAction {

	@Autowired
    private ChannelService channelService;


    @PreAuthorize("hasPermission('/portal/channel','save')")
    @ApiOperation(value = "保存频道", notes = "保存频道")
    @PostMapping("/save")
    public Channel saveChannel(@RequestBody ChannelDTO request){
        return channelService.saveChannel(request);
    }

    @PreAuthorize("hasPermission('/portal/channel','delete')")
    @ApiOperation(value = "删除频道", notes = "删除频道")
    @PostMapping("/delete")
    public void deleteChannelById(Long id){
        channelService.deleteChannelById(id);
    }

    @PreAuthorize("hasPermission('/portal/channel','read')")
    @ApiOperation(value = "获取频道列表", notes = "获取频道列表")
    @PostMapping("/page")
    public Page<Channel> getChannelPage(GetChannelListDTO request, Pageable pageable){
        return channelService.getChannelPage(request,pageable);
    }



}
