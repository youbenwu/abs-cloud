package com.outmao.ebs.portal.web.api;


import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.SimpleHotelDeviceVO;
import com.outmao.ebs.mall.order.vo.SettleVO;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertBuyOrder;
import com.outmao.ebs.portal.service.*;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
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
import java.util.ArrayList;
import java.util.List;


@Api(value = "portal-advert", tags = "门户-广告")
@RestController
@RequestMapping("/api/portal/advert")
public class AdvertAction {

	@Autowired
    private AdvertService advertService;

    @Autowired
    private AdvertChannelService advertChannelService;

    @Autowired
    private AdvertBuyOrderService advertBuyOrderService;

    @Autowired
    private AdvertPvLogService advertPvLogService;

    @Autowired
    private HotelDeviceService hotelDeviceService;


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

    @ApiOperation(value = "获取广告信息", notes = "获取广告信息")
    @PostMapping("/get")
    public AdvertVO getAdvertVOById(Long id){
        return advertService.getAdvertVOById(id);
    }

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "获取广告信息列表", notes = "获取广告信息列表")
    @PostMapping("/page")
    public Page<AdvertVO> getAdvertVOList(GetAdvertListDTO request,@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.DESC)Pageable pageable){
        return advertService.getAdvertVOPage(request,pageable);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取酒店设备广告信息列表", notes = "获取酒店设备广告信息列表")
    @PostMapping("/pageForHotelPad")
    public Page<AdvertVO> getAdvertPageForHotelPad(GetAdvertListForHotelPadDTO request, @PageableDefault(sort = {"sort"}, direction = Sort.Direction.ASC)Pageable pageable) {
        Page<AdvertVO> page= advertService.getAdvertVOPage(request,pageable);
        pvLog(page.getContent(),request.getDeviceNo());
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
        pvLog(list,request.getDeviceNo());
        return list;
    }

    private void pvLog(List<AdvertVO> list,String deviceNo){
        if(list==null||list.isEmpty())
            return;
        SimpleHotelDeviceVO device=null;
        if(deviceNo!=null){
            device=hotelDeviceService.getSimpleHotelDeviceVOByDeviceNo(deviceNo);
        }
        if(device==null){
            if(SecurityUtil.isAuthenticated()){
                device=hotelDeviceService.getSimpleHotelDeviceVOByUserId(SecurityUtil.currentUserId());
            }
        }
        if(device!=null){
            List<AdvertPvLogDTO> dtos=new ArrayList<>(list.size());
            for (AdvertVO advert:list){
                AdvertPvLogDTO dto=new AdvertPvLogDTO();
                dto.setUserId(device.getUserId());
                dto.setSpaceId(device.getHotelId());
                dto.setAdvertType(advert.getType());
                dto.setAdvertId(advert.getId());
                if(advert.getBuy()!=null) {
                    dto.setAmount(advert.getBuy().getPrice());
                }
                dto.setIncomeType(HotelDeviceIncomeType.AdvertCPM.getType());
                dtos.add(dto);
            }
            advertPvLogService.saveAdvertPvLogListAsync(dtos);
        }

    }


    @ApiOperation(value = "广告点击记录", notes = "广告点击记录")
    @PostMapping("/pv")
    public void pv(AdvertPvLogRequest request) {
        advertPvLogService.saveAdvertPvLog(request);
    }


    @ApiOperation(value = "广告投放下单", notes = "广告投放下单")
    @PostMapping("/saveOrder")
    public AdvertBuyOrder saveAdvertOrder(AdvertOrderDTO request){
        return advertBuyOrderService.saveAdvertOrder(request);
    }

    @ApiOperation(value = "获取广告投放结算金额", notes = "获取广告投放结算金额")
    @PostMapping("/settleOrder")
    public SettleVO settleAdvertOrder(AdvertOrderSettleDTO request){
        return advertBuyOrderService.settleAdvertOrder(request);
    }

    @ApiOperation(value = "迁眼广告按酒店统计", notes = "迁眼广告按酒店统计")
    @PostMapping("/qy/statsByHotel")
    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId){
        return advertPvLogService.getQyStatsAdvertByHotelVOList(advertId);
    }


}
