package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.SimpleHotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import com.outmao.ebs.user.vo.UserDetailsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HotelDeviceService {


    public HotelDevice saveHotelDevice(PadRegisterHotelDeviceDTO request);

    public HotelDevice saveHotelDevice(HotelDeviceDTO request);

    public void deleteHotelDeviceById(Long id);

    public long getHotelDeviceCount();

    public long getHotelDeviceCountByPartnerId(Long partnerId);

    public long getHotelDeviceCountByLeaseRenterId(Long leaseRenterId);

    public HotelDevice getHotelDeviceByDeviceNo(String deviceNo);

    public HotelDevice getHotelDeviceByUserId(Long userId);

    public HotelDeviceVO getHotelDeviceVOById(Long id);

    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo);


    public SimpleHotelDeviceVO getSimpleHotelDeviceVOByDeviceNo(String deviceNo);
    public SimpleHotelDeviceVO getSimpleHotelDeviceVOByUserId(Long userId);

    public List<SimpleHotelDeviceVO> getSimpleHotelDeviceVOByUserIdIn(Collection<Long> userIdIn);

    public List<SimpleHotelDeviceVO> getSimpleHotelDeviceVOByUserIdInAndIsLease(Collection<Long> userIdIn);


    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request);

    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable);

    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size);

    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size);


    public List<UserDetailsVO> getUserDetailsVOListByHotelId(Long hotelId);


    /**
     *
     * 设备购买
     *
     **/
    public List<HotelDevice> buy(HotelDeviceBuyDTO request);


    /**
     *
     * 设备租赁
     *
     **/
    public List<HotelDevice> lease(HotelDeviceLeaseDTO request);


    /**
     *
     * 设备托管
     *
     **/
    public List<HotelDevice> deploy(List<HotelRoomDeviceDeployDTO> request);

}
