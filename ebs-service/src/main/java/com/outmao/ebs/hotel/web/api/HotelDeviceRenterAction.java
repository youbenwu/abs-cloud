package com.outmao.ebs.hotel.web.api;


import com.outmao.ebs.hotel.dto.HotelDeviceDeployDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceRenterDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "hotel-device-renter", tags = "酒店-设备-租户")
@RestController
@RequestMapping("/api/hotel/device/renter")
public class HotelDeviceRenterAction {

	@Autowired
    private HotelDeviceLeaseService hotelDeviceLeaseService;

    @Autowired
    private HotelDeviceIncomeService hotelDeviceIncomeService;


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

    @ApiOperation(value = "酒店设备托管", notes = "酒店设备托管")
    @PostMapping("/order/deploy")
    public void hotelDeviceDeploy(@RequestBody HotelDeviceDeployDTO request){
        hotelDeviceLeaseService.hotelDeviceDeploy(request);
    }

    @PreAuthorize("permitAll")
    @ApiOperation(value = "单个设备租赁收益统计", notes = "单个设备租赁收益统计")
    @PostMapping("/income/stats/device")
    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId){
        return hotelDeviceIncomeService.getRenterHotelDeviceIncomeStatsVO(renterId,deviceId);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "租户租赁总收益统计", notes = "租户租赁总收益统计")
    @PostMapping("/income/stats")
    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId){
        return hotelDeviceIncomeService.getRenterTotalHotelDeviceIncomeStatsVO(renterId);
    }


    @PreAuthorize("permitAll")
    @ApiOperation(value = "租户租赁总收益按类型统计", notes = "租户租赁总收益按类型统计")
    @PostMapping("/income/type/stats")
    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId){
        return hotelDeviceIncomeService.getRenterHotelDeviceIncomeTypeStatsVOList(renterId);
    }



}
