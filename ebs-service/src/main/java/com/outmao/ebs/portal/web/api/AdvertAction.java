package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;
import com.outmao.ebs.portal.service.AdvertBuyDisplayOrderService;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import com.outmao.ebs.portal.vo.AdvertVO;
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
import org.springframework.web.bind.annotation.RequestBody;
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
    private AdvertBuyDisplayOrderService advertBuyDisplayOrderService;

    @Autowired
    private AdvertPvLogService advertPvLogService;


    @ApiOperation(value = "获取广告频道列表", notes = "获取广告频道列表")
    @PostMapping("/channel/list")
    public List<AdvertChannelVO> getAdvertChannelVOList(GetAdvertChannelListDTO request) {
        return advertChannelService.getAdvertChannelVOList(request);
    }


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存广告信息", notes = "保存广告信息")
    @PostMapping("/save")
    public Advert saveAdvert(@RequestBody AdvertDTO request) {
        return advertService.saveAdvert(request);
    }


    @ApiOperation(value = "设置广告上下架", notes = "设置广告上下架")
    @PostMapping("/display")
    public Advert setAdvertDisplay(SetAdvertDisplayDTO request){
        return advertService.setAdvertDisplay(request);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取酒店设备广告信息列表", notes = "获取酒店设备广告信息列表")
    @PostMapping("/pageForHotelPad")
    public Page<AdvertVO> getAdvertPageForHotelPad(GetAdvertListForHotelPadDTO request, @PageableDefault(sort = {"sort"}, direction = Sort.Direction.ASC)Pageable pageable) {
        Page<AdvertVO> page= advertService.getAdvertVOPage(request,pageable);
        pvLog(page.getContent());
        return page;
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表 channelCode--" +
            "默认广告:pad-home-def" +
            "首页广告:pad-home" +
            "副页广告:pad-sub")
    @PostMapping("/listForHotelPad")
    public List<AdvertVO> getAdvertList(GetAdvertListForHotelPadDTO request) {
        List<AdvertVO> list= advertService.getAdvertVOList(request);
        pvLog(list);
        return list;
    }

    private void pvLog(List<AdvertVO> list){
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
    public AdvertBuyDisplayOrder saveAdvertOrder(AdvertOrderDTO request){
        return advertBuyDisplayOrderService.saveAdvertOrder(request);
    }

    @ApiOperation(value = "获取广告投放结算金额", notes = "获取广告投放结算金额")
    @PostMapping("/settleOrder")
    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request){
        return advertBuyDisplayOrderService.settleAdvertOrder(request);
    }


}
