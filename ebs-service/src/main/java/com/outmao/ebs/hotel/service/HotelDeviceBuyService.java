package com.outmao.ebs.hotel.service;

import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelDeviceBuyService {


    public HotelDeviceOwner saveHotelDeviceOwner(HotelDeviceOwnerDTO request);

    public HotelDeviceOwner saveHotelDeviceOwnerBuy(HotelDeviceOwnerBuyDTO request);

    public HotelDeviceOwner getHotelDeviceOwnerByUserId(Long userId);

    public HotelDeviceOwnerVO getHotelDeviceOwnerVOByUserId(Long userId);

    public Page<HotelDeviceOwnerVO> getHotelDeviceOwnerVOPage(GetHotelDeviceOwnerListDTO request, Pageable pageable);



}
