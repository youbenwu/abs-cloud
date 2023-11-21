package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.PadRegisterHotelDeviceDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.qrCode.dto.GenerateQrCodeDTO;
import com.outmao.ebs.qrCode.service.QrCodeService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "hotel", tags = "酒店")
@RestController
@RequestMapping("/api/hotel")
public class HotelAction {

	@Autowired
    private HotelService hotelService;

	@Autowired
    private QrCodeService qrCodeService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "注册酒店", notes = "注册酒店")
    @PostMapping("/register")
    public void registerHotel(@RequestBody RegisterHotelDTO request) {
        hotelService.registerHotel(request);
        try{
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "获取酒店信息", notes = "获取酒店信息")
    @PostMapping("/get")
    public HotelVO getHotelVOById(Long id) {
        return hotelService.getHotelVOById(id);
    }


    @ApiOperation(value = "平板激活设备", notes = "平板激活设备")
    @PostMapping("/device/register")
    public void registerHotelDevice(@RequestBody PadRegisterHotelDeviceDTO request) {
        if(request.getUserId()==null){
            request.setUserId(SecurityUtil.currentUserId());
        }
        hotelService.saveHotelDevice(request);
    }


    @ApiOperation(value = "获取设备", notes = "获取设备")
    @PostMapping("/device/getByDeviceNo")
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo){
        return hotelService.getHotelDeviceVOByDeviceNo(deviceNo);
    }



    @ApiOperation(value = "获取设备列表", notes = "获取设备列表")
    @PostMapping("/device/list")
    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request) {
        return hotelService.getHotelDeviceVOList(request);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "设备按城市统计数量和金额", notes = "设备按城市统计数量和金额")
    @PostMapping("/device/stats/city")
    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size) {
        return hotelService.getStatsHotelDeviceCityVOList(size);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "设备按省统计数量和金额", notes = "设备按省统计数量和金额")
    @PostMapping("/device/stats/province")
    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size) {
        return hotelService.getStatsHotelDeviceProvinceVOList(size);
    }


    @ApiOperation(value = "获取机主信息", notes = "获取机主信息")
    @PostMapping("/device/owner/get")
    public HotelDeviceOwnerVO getHotelDeviceOwnerByUserId(Long userId){
        return hotelService.getHotelDeviceOwnerVOByUserId(userId);
    }


    @ApiOperation(value = "获取购买二维码", notes = "通过微信扫描二维码，进入小程序支付")
    @PostMapping("/mpBuyQrCode")
    public MpBuyQrcodeVO getMpBuyQrcode(Long productId){

        Long userId=SecurityUtil.currentUserId();

        HotelDevice device=hotelService.getHotelDeviceByUserId(userId);


        String url="https://mp.qyhuyu.cn/product/buy?productId="+productId+"&hotelId="+device.getHotelId()+"&roomNo="+device.getRoomNo();

        System.out.println(url);

        GenerateQrCodeDTO codeDTO=new GenerateQrCodeDTO();
        codeDTO.setCode(url);
        codeDTO.setWidth(500);
        codeDTO.setHeight(500);
        String qrcode=qrCodeService.generateQrCode(codeDTO);

        MpBuyQrcodeVO vo=new MpBuyQrcodeVO();
        vo.setTitle("请用微信扫描二维码购买");
        vo.setQrCode(qrcode);

        return vo;
    }




}
