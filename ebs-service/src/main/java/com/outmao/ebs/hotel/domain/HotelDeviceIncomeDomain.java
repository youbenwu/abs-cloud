package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;

import java.util.List;

public interface HotelDeviceIncomeDomain {


    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId);

    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId);

    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId);


}
