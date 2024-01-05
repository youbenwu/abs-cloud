package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceIncomeListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceIncomeDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceIncome;
import com.outmao.ebs.hotel.vo.HotelDeviceIncomeVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelDeviceIncomeDomain {


    public HotelDeviceIncome saveHotelDeviceIncome(HotelDeviceIncomeDTO request);

    public Page<HotelDeviceIncomeVO> getHotelDeviceIncomeVOPage(GetHotelDeviceIncomeListDTO request, Pageable pageable);

    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId);

    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId);

    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId);


}
