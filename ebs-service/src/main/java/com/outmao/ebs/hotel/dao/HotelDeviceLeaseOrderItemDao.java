package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceLeaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelDeviceLeaseOrderItemDao extends JpaRepository<HotelDeviceLeaseOrderItem,Long> {

    public List<HotelDeviceLeaseOrderItem> findAllByOrderId(Long orderId);

    public List<HotelDeviceLeaseOrderItem> findAllByDeviceId(Long deviceId);

    @Query("select e.deviceId from HotelDeviceLeaseOrderItem e where e.orderId=?1")
    public List<Long> findAllDeviceIdByOrderId(Long orderId);

}
