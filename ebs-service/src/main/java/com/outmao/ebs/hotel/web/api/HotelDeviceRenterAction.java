package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.HotelDeviceRenterDTO;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "hotel-device-renter", tags = "酒店-设备-租户")
@RestController
@RequestMapping("/api/hotel/device/renter")
public class HotelDeviceRenterAction {

	@Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;


    @PreAuthorize("principal.id.equals(#request.userId)")
    @ApiOperation(value = "保存租户信息", notes = "保存租户信息")
    @PostMapping("/save")
    public void saveHotelDeviceRenter(HotelDeviceRenterDTO request){
        hotelDeviceLeaseService.saveHotelDeviceRenter(request);
    }

    @PreAuthorize("principal.id.equals(#userId)")
    @ApiOperation(value = "读取租户信息", notes = "读取租户信息")
    @PostMapping("/get")
    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId){
        return hotelDeviceLeaseService.getHotelDeviceRenterVOByUserId(userId);
    }


}
