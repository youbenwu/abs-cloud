package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.domain.HotelDeviceIncomeDomain;
import com.outmao.ebs.hotel.service.HotelDeviceIncomeService;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HotelDeviceIncomeServiceImpl extends BaseService implements HotelDeviceIncomeService {


    @Autowired
    private HotelDeviceIncomeDomain hotelDeviceIncomeDomain;


    @Override
    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId) {

        return hotelDeviceIncomeDomain.getRenterHotelDeviceIncomeStatsVO(renterId,deviceId);

    }


    @Override
    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId) {
        return hotelDeviceIncomeDomain.getRenterTotalHotelDeviceIncomeStatsVO(renterId);
    }


    @Override
    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId) {
        return hotelDeviceIncomeDomain.getRenterHotelDeviceIncomeTypeStatsVOList(renterId);
    }


}
