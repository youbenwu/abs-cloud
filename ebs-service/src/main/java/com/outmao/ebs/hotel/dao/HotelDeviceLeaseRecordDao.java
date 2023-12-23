package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDeviceLeaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface HotelDeviceLeaseRecordDao extends JpaRepository<HotelDeviceLeaseRecord,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select r from HotelDeviceLeaseRecord r where r.deviceId=?1 and r.userId=?2")
    public HotelDeviceLeaseRecord findByDeviceIdAndUserIdLock(Long deviceId,Long userId);


}
