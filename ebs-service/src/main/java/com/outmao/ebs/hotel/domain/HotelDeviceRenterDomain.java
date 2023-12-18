package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelDeviceRenterListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceRenterDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceRenterLeaseDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface HotelDeviceRenterDomain {


    public HotelDeviceRenter saveHotelDeviceRenter(HotelDeviceRenterDTO request);

    public HotelDeviceRenter saveHotelDeviceRenterLease(HotelDeviceRenterLeaseDTO request);

    public HotelDeviceRenter getHotelDeviceRenterByUserId(Long userId);

    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId);

    public Page<HotelDeviceRenterVO> getHotelDeviceRenterVOPage(GetHotelDeviceRenterListDTO request, Pageable pageable);


    public List<MinHotelDeviceRenterVO> getMinHotelDeviceRenterVOListByUserIdIn(Collection<Long> userIdIn);


}
