package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelDeviceRenterDao extends JpaRepository<HotelDeviceRenter,Long> {

    public HotelDeviceRenter findByUserId(Long userId);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from HotelDeviceRenter c where c.userId=?1")
    public HotelDeviceRenter findByUserIdLock(Long userId);

}
