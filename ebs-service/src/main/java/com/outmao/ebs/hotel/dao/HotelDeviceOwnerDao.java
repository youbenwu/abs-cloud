package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;

public interface HotelDeviceOwnerDao extends JpaRepository<HotelDeviceOwner,Long>, QuerydslPredicateExecutor<HotelDeviceOwner> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from HotelDeviceOwner c where c.userId=?1")
    public HotelDeviceOwner findByUserId(Long userId);

}
