package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRoomTypeDao extends JpaRepository<HotelRoomType,Long> {


    public HotelRoomType findByHotelIdAndName(Long hotelId,String name);

}
