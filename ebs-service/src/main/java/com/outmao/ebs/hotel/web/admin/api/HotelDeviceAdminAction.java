package com.outmao.ebs.hotel.web.admin.api;


import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@Api(value = "admin-hotel-device", tags = "后台-酒店-设备")
@RestController
@RequestMapping("/api/admin/hotel/device")
public class HotelDeviceAdminAction {

	@Autowired
    private HotelDeviceService hotelDeviceService;


    @PreAuthorize("hasPermission('/hotel/device','save')")
    @ApiOperation(value = "保存设备", notes = "保存设备")
    @PostMapping("/save")
    public void saveHotelDevice(HotelDeviceDTO request){
        hotelDeviceService.saveHotelDevice(request);
    }

    @PreAuthorize("hasPermission('/hotel/device','delete')")
    @ApiOperation(value = "删除设备", notes = "删除设备")
    @PostMapping("/delete")
    public void deleteHotelDeviceById(Long id){
        hotelDeviceService.deleteHotelDeviceById(id);
    }


    @ApiOperation(value = "获取设备数量", notes = "获取设备数量")
    @PostMapping("/count")
    public long getHotelDeviceCount() {
        return hotelDeviceService.getHotelDeviceCount();
    }

    @PreAuthorize("hasPermission('/hotel/device','read')")
    @ApiOperation(value = "获取设备", notes = "获取设备")
    @PostMapping("/get")
    public HotelDeviceVO getHotelDeviceVOById(Long id){
        return hotelDeviceService.getHotelDeviceVOById(id);
    }

    @PreAuthorize("hasPermission('/hotel/device','read')")
    @ApiOperation(value = "获取设备", notes = "获取设备")
    @PostMapping("/getByDeviceNo")
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo){
        return hotelDeviceService.getHotelDeviceVOByDeviceNo(deviceNo);
    }

    @PreAuthorize("hasPermission('/hotel/device','read')")
    @ApiOperation(value = "获取设备列表", notes = "获取设备列表")
    @PostMapping("/list")
    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request){
        return hotelDeviceService.getHotelDeviceVOList(request);
    }

    @PreAuthorize("hasPermission('/hotel/device','read')")
    @ApiOperation(value = "获取设备列表", notes = "获取设备列表")
    @PostMapping("/page")
    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable){
        return hotelDeviceService.getHotelDeviceVOPage(request,pageable);
    }


}
