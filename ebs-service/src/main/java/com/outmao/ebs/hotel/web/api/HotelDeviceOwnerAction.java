package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.service.HotelDeviceBuyService;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "hotel-device-owner", tags = "酒店-设备-机主")
@RestController
@RequestMapping("/api/hotel/device/owner")
public class HotelDeviceOwnerAction {

	@Autowired
    private HotelDeviceBuyService hotelDeviceBuyService;

    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存机主信息", notes = "保存机主信息")
    @PostMapping("/save")
    public void saveHotelDeviceOwner(HotelDeviceOwnerDTO request){
        hotelDeviceBuyService.saveHotelDeviceOwner(request);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "获取机主信息", notes = "获取机主信息")
    @PostMapping("/get")
    public HotelDeviceOwnerVO getHotelDeviceOwnerVOByUserId(Long userId){
        return hotelDeviceBuyService.getHotelDeviceOwnerVOByUserId(userId);
    }


}
