package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelDeviceDao extends JpaRepository<HotelDevice,Long> {

    public HotelDevice findByDeviceNo(String deviceNo);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select r from HotelDevice r where r.id=?1")
    public HotelDevice findByIdForUpdate(Long id);

    public HotelDevice findByUserId(Long userId);

}
