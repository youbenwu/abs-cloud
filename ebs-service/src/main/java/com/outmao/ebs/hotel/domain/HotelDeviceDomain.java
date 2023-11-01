package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceNewDTO;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelDeviceDomain {


    public HotelDevice saveHotelDevice(HotelDeviceDTO request);

    public HotelDevice saveHotelDevice(HotelDeviceNewDTO request);

    public void deleteHotelDeviceById(Long id);

    public long getHotelDeviceCount();

    public long getHotelDeviceCountByPartnerId(Long partnerId);

    public List<HotelDevice> getHotelDeviceListByOwnerId(Long ownerId);

    public List<HotelDevice> getHotelDeviceListByPartnerId(Long partnerId);

    public HotelDevice getHotelDeviceByUserId(Long userId);

    public HotelDeviceVO getHotelDeviceVOById(Long id);

    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo);


    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request);

    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable);


    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size);


    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size);


}
