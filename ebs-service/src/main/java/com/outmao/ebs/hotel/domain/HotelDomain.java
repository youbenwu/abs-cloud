package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.hotel.vo.QyHotelVO;
import com.outmao.ebs.hotel.vo.SimpleHotelVO;
import com.outmao.ebs.hotel.vo.StatsHotelCountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface HotelDomain {

    public Hotel registerHotel(RegisterHotelDTO request);

    public Hotel saveHotel(HotelDTO request);

    public Hotel getHotelByUserId(Long userId);

    public Hotel setHotelStatus(SetHotelStatusDTO request);

    public long getHotelCount();

    public HotelVO getHotelVOById(Long id);

    public HotelVO getHotelVOByOrgId(Long orgId);

    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable);


    public List<HotelVO> getHotelVOListByOrgIdIn(Collection<Long> orgIdIn);

    public List<HotelVO> getHotelVOListByIdIn(Collection<Long> idIn);

    public List<SimpleHotelVO> getSimpleHotelVOListByIdIn(Collection<Long> idIn);

    public List<StatsHotelCountVO> getStatsHotelCountVOListByDays(Date fromTime, Date toTime);

    public List<StatsHotelCountVO> getStatsHotelCountVOListByMonths(Date fromTime, Date toTime);

    public QyHotelVO getQyHotelVOById(Long id);

    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListDTO request, Pageable pageable);

    public Page<QyHotelVO> getQyHotelVOPage(GetHotelListForDeployDeviceDTO request, Pageable pageable);

}
