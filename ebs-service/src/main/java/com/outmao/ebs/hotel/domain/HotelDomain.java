package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelListDTO;
import com.outmao.ebs.hotel.dto.HotelDTO;
import com.outmao.ebs.hotel.dto.RegisterHotelDTO;
import com.outmao.ebs.hotel.dto.SetHotelStatusDTO;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.vo.HotelVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelDomain {

    public Hotel registerHotel(RegisterHotelDTO request);

    public Hotel saveHotel(HotelDTO request);

    public Hotel setHotelStatus(SetHotelStatusDTO request);

    public HotelVO getHotelVOById(Long id);

    public HotelVO getHotelVOByOrgId(Long orgId);

    public Page<HotelVO> getHotelVOPage(GetHotelListDTO request, Pageable pageable);


}
