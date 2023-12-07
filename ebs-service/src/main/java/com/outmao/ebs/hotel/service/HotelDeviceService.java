package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelDeviceService {


    public HotelDevice saveHotelDevice(PadRegisterHotelDeviceDTO request);

    public HotelDevice saveHotelDevice(HotelDeviceDTO request);

    public void deleteHotelDeviceById(Long id);

    public long getHotelDeviceCount();

    public long getHotelDeviceCountByPartnerId(Long partnerId);

    public HotelDevice getHotelDeviceByUserId(Long userId);

    public HotelDeviceVO getHotelDeviceVOById(Long id);

    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo);

    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request);

    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable);

    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size);

    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size);



}
