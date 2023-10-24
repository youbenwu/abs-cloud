package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.PadRegisterHotelDeviceDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "hotel", tags = "酒店")
@RestController
@RequestMapping("/api/hotel")
public class HotelAction {

	@Autowired
    private HotelService hotelService;


    @PreAuthorize("permitAll")
    @ApiOperation(value = "注册酒店", notes = "注册酒店")
    @PostMapping("/register")
    public void registerHotel(@RequestBody RegisterHotelDTO request) {
         hotelService.registerHotel(request);
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


}
