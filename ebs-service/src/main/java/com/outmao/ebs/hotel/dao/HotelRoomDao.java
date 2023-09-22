package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelRoomDao extends JpaRepository<HotelRoom,Long> {


    public HotelRoom findByHotelIdAndRoomNo(Long hotelId,String roomNo);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select r from HotelRoom r where r.id=?1")
    public HotelRoom findByIdForUpdate(Long id);

}
