package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelDeviceLeaseOrderDao extends JpaRepository<HotelDeviceLeaseOrder,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from HotelDeviceLeaseOrder o where o.id=?1")
    public HotelDeviceLeaseOrder findByIdLock(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from HotelDeviceLeaseOrder o where o.orderNo=?1")
    public HotelDeviceLeaseOrder findByOrderNoLock(String orderNo);

    public HotelDeviceLeaseOrder findByOrderNo(String orderNo);

}
