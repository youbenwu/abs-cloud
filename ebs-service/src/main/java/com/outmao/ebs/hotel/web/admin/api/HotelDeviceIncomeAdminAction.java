package com.outmao.ebs.hotel.web.admin.api;


import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.common.constant.HotelDeviceStatus;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.service.HotelDeviceLeaseService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.service.scheduler.IncomeCalculateJob;
import com.outmao.ebs.hotel.vo.*;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.wallet.dto.TradeRechargeDTO;
import com.outmao.ebs.wallet.service.TradeService;
import com.outmao.ebs.wallet.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Api(value = "admin-hotel-device-income", tags = "后台-酒店-设备-收益")
@RestController
@RequestMapping("/api/admin/hotel/device/income")
public class HotelDeviceIncomeAdminAction {



    @Autowired
    private HotelDeviceIncomeService hotelDeviceIncomeService;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    @Autowired
    private IncomeCalculateJob incomeCalculateJob;


    @ApiOperation(value = "获取设备收益记录", notes = "获取设备收益记录")
    @PostMapping("/page")
    public Page<HotelDeviceIncomeVO> getHotelDeviceIncomeVOPage(GetHotelDeviceIncomeListDTO request, Pageable pageable){
        return hotelDeviceIncomeService.getHotelDeviceIncomeVOPage(request,pageable);
    }


    @ApiOperation(value = "测试计算设备收益", notes = "测试计算设备收益")
    @PostMapping("/calculateJob")
    public void incomeCalculateJob(String password){
        if(!password.equals("83419276"))
            return;
        incomeCalculateJob.process();
    }

    //测试添加设备收益
    @ApiOperation(value = "测试添加设备收益", notes = "测试添加设备收益")
    @PostMapping("/test")
    public void testAddIncome(String password){
        if(!password.equals("83419276"))
            return;
        Date date=new Date();
        testAddIncome(date);
    }


    //测试添加设备收益
    private void testAddIncome(Date date){
        //查出所有激活的设备
        GetHotelDeviceListDTO request=new GetHotelDeviceListDTO();
        request.setStatus(Arrays.asList(HotelDeviceStatus.Active.getStatus()));
        List<HotelDeviceVO> devices=hotelDeviceService.getHotelDeviceVOList(request);

        devices.forEach(t->{
            testAddIncome(t,date);
        });

    }

    private void testAddIncome(HotelDeviceVO device,Date date){
        HotelDeviceIncomeType[] types=HotelDeviceIncomeType.values();


        for(HotelDeviceIncomeType type:types){

            //每一种收益都加数据
            double totalAmount= new Random().nextInt(300);
            if(totalAmount<10){
                totalAmount+=10;
            }

            HotelDeviceIncomeDTO dto=new HotelDeviceIncomeDTO();
            dto.setDeviceId(device.getId());
            dto.setType(type.getType());
            dto.setTotalAmount(totalAmount);
            dto.setTotalFee(totalAmount*0.20);
            if(device.getLease()!=null&&device.getLease().getStatus()==1) {
                dto.setRenterId(device.getLease().getRenterId());
                dto.setRenterFee(dto.getTotalFee()*0.3);
            }
            dto.setFee(dto.getTotalFee()-dto.getRenterFee());

            dto.setTime(date);
            dto.setRemark(type.getDescription()+"收益");

            dto.setStatus(0);

            hotelDeviceIncomeService.saveHotelDeviceIncome(dto);

        }


    }



}
