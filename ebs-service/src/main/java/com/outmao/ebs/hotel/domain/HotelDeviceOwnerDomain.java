package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceOwnerListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerBuyDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HotelDeviceOwnerDomain {


    public HotelDeviceOwner saveHotelDeviceOwner(HotelDeviceOwnerDTO request);

    public HotelDeviceOwner saveHotelDeviceOwnerBuy(HotelDeviceOwnerBuyDTO request);

    public HotelDeviceOwner getHotelDeviceOwnerByUserId(Long userId);

    public HotelDeviceOwnerVO getHotelDeviceOwnerVOByUserId(Long userId);


    public Page<HotelDeviceOwnerVO> getHotelDeviceOwnerVOPage(GetHotelDeviceOwnerListDTO request, Pageable pageable);


}
