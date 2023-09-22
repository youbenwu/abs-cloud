package com.outmao.ebs.portal.web.api;



import com.outmao.ebs.portal.dto.GetChannelListDTO;
import com.outmao.ebs.portal.entity.Channel;
import com.outmao.ebs.portal.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@Api(value = "portal-channel", tags = "门户-频道")
@RestController
@RequestMapping("/api/portal/channel")
public class ChannelAction {

	@Autowired
    private ChannelService channelService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取频道列表", notes = "获取频道列表")
    @PostMapping("/page")
    public Page<Channel> getChannelPage(GetChannelListDTO request, Pageable pageable){
        return channelService.getChannelPage(request,pageable);
    }



}
