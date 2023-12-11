package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceDTO;
import com.outmao.ebs.hotel.dto.PadRegisterHotelDeviceDTO;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;
import com.outmao.ebs.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value = "hotel-device", tags = "酒店-设备")
@RestController
@RequestMapping("/api/hotel/device")
public class HotelDeviceAction {

	@Autowired
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "平板激活设备", notes = "平板激活设备")
    @PostMapping("/save")
    public void saveHotelDevice(HotelDeviceDTO request){
        hotelDeviceService.saveHotelDevice(request);
    }


    @ApiOperation(value = "平板注册酒店并激活设备", notes = "平板注册酒店并激活设备")
    @PostMapping("/register")
    public void registerHotelDevice(@RequestBody PadRegisterHotelDeviceDTO request) {
        if(request.getUserId()==null){
            request.setUserId(SecurityUtil.currentUserId());
        }
        hotelDeviceService.saveHotelDevice(request);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "获取设备", notes = "获取设备")
    @PostMapping("/getByDeviceNo")
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo){
        HotelDeviceVO vo= hotelDeviceService.getHotelDeviceVOByDeviceNo(deviceNo);
        if(vo!=null){
            //自动登录
            UserOauth oauth=userService.getUserAuthByPrincipal(vo.getDeviceNo());
            SetAuthenticatedDTO  dto=new SetAuthenticatedDTO();
            dto.setOauthId(oauth.getId());
            dto.setUserId(oauth.getUser().getId());
            UserOauthSession session=userService.setAuthenticated(dto);
            vo.setToken(session.getToken());
        }
        return vo;
    }

    @ApiOperation(value = "获取设备列表", notes = "获取设备列表")
    @PostMapping("/list")
    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request) {
        return hotelDeviceService.getHotelDeviceVOList(request);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "设备按城市统计数量和金额", notes = "设备按城市统计数量和金额")
    @PostMapping("/stats/city")
    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size) {
        return hotelDeviceService.getStatsHotelDeviceCityVOList(size);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "设备按省统计数量和金额", notes = "设备按省统计数量和金额")
    @PostMapping("/stats/province")
    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size) {
        return hotelDeviceService.getStatsHotelDeviceProvinceVOList(size);
    }



}
