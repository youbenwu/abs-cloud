package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDevice;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;

public interface HotelDeviceDao extends JpaRepository<HotelDevice,Long> {

    public HotelDevice findByDeviceNo(String deviceNo);

    public HotelDevice findByUserId(Long userId);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.id=?1")
    public HotelDevice findByIdLock(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.deviceNo=?1")
    public HotelDevice findByDeviceNoLock(String deviceNo);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.status=0")
    public List<HotelDevice> findAllByNoActivateLock(PageRequest request);


    public long countByBuyPartnerId(Long buyPartnerId);

    public List<HotelDevice> findAllByBuyOwnerId(Long buyOwnerId);

    public List<HotelDevice> findAllByBuyPartnerId(Long buyPartnerId);

    public long countByLeaseRenterId(Long leaseRenterId);


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.lease.status in ?1")
    public List<HotelDevice> findAllByLeaseStatusInLock(Integer[] leaseStatusIn,PageRequest request);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.lease.status=1 and d.lease.endTime<?1")
    public List<HotelDevice> findAllByLeaseExpireLock(Date time);

    @Modifying
    @Query("update HotelDevice d set d.province=?2 , d.city=?3 where d.hotelId=?1")
    public void updateAddress(Long hotelId,String province,String city);


}
