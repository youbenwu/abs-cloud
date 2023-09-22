package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelCustomerStay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;

public interface HotelCustomerStayDao extends JpaRepository<HotelCustomerStay,Long> , QuerydslPredicateExecutor<HotelCustomerStay> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from HotelCustomerStay c where c.id=?1")
    public HotelCustomerStay findByIdForUpdate(Long id);


}
