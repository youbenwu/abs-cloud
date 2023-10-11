package com.outmao.ebs.hotel.domain;

import com.outmao.ebs.hotel.dto.GetHotelRoomTypeListDTO;
import com.outmao.ebs.hotel.dto.HotelRoomTypeDTO;
import com.outmao.ebs.hotel.entity.HotelRoomType;
import com.outmao.ebs.hotel.vo.HotelRoomTypeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelRoomTypeDomain {


    public HotelRoomType saveHotelRoomType(HotelRoomTypeDTO request);

    public void deleteHotelRoomTypeById(Long id);

    public HotelRoomTypeVO getHotelRoomTypeVOById(Long id);

    public Page<HotelRoomTypeVO> getHotelRoomTypeVOPage(GetHotelRoomTypeListDTO request, Pageable pageable);


}
