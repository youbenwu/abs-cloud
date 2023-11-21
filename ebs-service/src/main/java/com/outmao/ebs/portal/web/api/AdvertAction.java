package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.entity.AdvertOrder;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "portal-advert", tags = "门户-广告")
@RestController
@RequestMapping("/api/portal/advert")
public class AdvertAction {

	@Autowired
    private AdvertService advertService;

    @Autowired
    private AdvertChannelService advertChannelService;

    @Autowired
    private AdvertPvLogService advertPvLogService;


    @ApiOperation(value = "获取广告频道列表", notes = "获取广告频道列表")
    @PostMapping("/channel/list")
    public List<AdvertChannel> getAdvertChannelList(GetAdvertChannelListDTO request) {
        return advertChannelService.getAdvertChannelList(request);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表")
    @PostMapping("/page")
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, @PageableDefault(sort = {"sort"}, direction = Sort.Direction.ASC)Pageable pageable) {
        request.setStatus(1);
        Page<Advert> page= advertService.getAdvertPage(request,pageable);
        pvLog(page.getContent());
        return page;
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表 channelCode--" +
            "默认广告:pad-home-def" +
            "首页广告:pad-home" +
            "副页广告:pad-sub")
    @PostMapping("/list")
    public List<Advert> getAdvertList(String channelCode,int size) {
        List<Advert> list= advertService.getAdvertList(channelCode,size);
        if(list.isEmpty()){
            list=advertService.getAdvertList("pad-home-def",size);
        }
        pvLog(list);
        return list;
    }

    private void pvLog(List<Advert> list){
        if(list==null||list.isEmpty())
            return;
        if(SecurityUtil.isAuthenticated()) {
            SaveAdvertPvLogListDTO listDTO = new SaveAdvertPvLogListDTO();
            listDTO.setAdverts(list.stream().map(t -> t.getId()).collect(Collectors.toList()));
            listDTO.setUserId(SecurityUtil.currentUserId());
            advertPvLogService.saveAdvertPvLogListAsync(listDTO);
        }
    }


    @ApiOperation(value = "广告投放下单", notes = "广告投放下单")
    @PostMapping("/saveOrder")
    public AdvertOrder saveAdvertOrder(AdvertOrderDTO request){
        return advertService.saveAdvertOrder(request);
    }

    @ApiOperation(value = "获取广告投放结算金额", notes = "获取广告投放结算金额")
    @PostMapping("/settleOrder")
    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request){
        return advertService.settleAdvertOrder(request);
    }


}
