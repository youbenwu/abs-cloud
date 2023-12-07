package com.outmao.ebs.hotel.web.admin.api;



import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-hotel-device-renter", tags = "后台-酒店-设备-租户")
@RestController
@RequestMapping("/api/admin/hotel/device/renter")
public class HotelDeviceRenterAdminAction {

	@Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;


    @PreAuthorize("hasPermission('/hotel/device/renter','save')")
    @ApiOperation(value = "保存租户信息", notes = "保存租户信息")
    @PostMapping("/save")
    public void saveHotelDeviceRenter(HotelDeviceRenterDTO request){
        hotelDeviceLeaseService.saveHotelDeviceRenter(request);
    }

    @PreAuthorize("hasPermission('/hotel/device/renter','read')")
    @ApiOperation(value = "读取租户信息", notes = "读取租户信息")
    @PostMapping("/get")
    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId){
        return hotelDeviceLeaseService.getHotelDeviceRenterVOByUserId(userId);
    }

    @PreAuthorize("hasPermission('/hotel/device/renter','read')")
    @ApiOperation(value = "读取租户信息列表", notes = "读取租户信息列表")
    @PostMapping("/page")
    public Page<HotelDeviceRenterVO> getHotelDeviceRenterVOPage(GetHotelDeviceRenterListDTO request, Pageable pageable){
        return hotelDeviceLeaseService.getHotelDeviceRenterVOPage(request,pageable);
    }


}
