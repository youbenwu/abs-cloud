package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelCustomerDao extends JpaRepository<HotelCustomer,Long> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from HotelCustomer c where c.id=?1")
    public HotelCustomer findByIdForUpdate(Long id);

    public HotelCustomer findByHotelIdAndPhone(Long hotelId,String phone);

}
