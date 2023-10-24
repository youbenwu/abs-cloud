package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceOwnerListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelDeviceOwnerDomain {


    public HotelDeviceOwner saveHotelDeviceOwner(HotelDeviceOwnerDTO request);


    public HotelDeviceOwner addHotelDeviceOwnerIncome(Long userId,double addIncome);


    public HotelDeviceOwner getHotelDeviceOwnerByUserId(Long userId);


    public Page<HotelDeviceOwner> getHotelDeviceOwnerPage(GetHotelDeviceOwnerListDTO request, Pageable pageable);





}
