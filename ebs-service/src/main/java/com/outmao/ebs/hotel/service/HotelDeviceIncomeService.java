package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;

import java.util.List;

public interface HotelDeviceIncomeService {


    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId);

    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId);

    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId);



}
