package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelListDTO;
import com.outmao.ebs.hotel.dto.HotelDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.dto.SetHotelStatusDTO;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.hotel.vo.StatsHotelCountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface HotelDomain {

    public Hotel registerHotel(RegisterHotelDTO request);

    public Hotel saveHotel(HotelDTO request);

    public Hotel setHotelStatus(SetHotelStatusDTO request);

    public long getHotelCount();

    public HotelVO getHotelVOById(Long id);

    public HotelVO getHotelVOByOrgId(Long orgId);

    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable);

    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn);

    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime);

    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime);

}
