package com.outmao.ebs.hotel.dao;

import com.outmao.ebs.hotel.entity.HotelDevice;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface HotelDeviceDao extends JpaRepository<HotelDevice,Long> {

    public HotelDevice findByDeviceNo(String deviceNo);

    public HotelDevice findByUserId(Long userId);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.hotelId=?1 and d.roomNo=?2")
    public HotelDevice findByHotelIdAndRoomNoLock(Long hotelId,String roomNo);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.id=?1")
    public HotelDevice findByIdLock(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.deviceNo=?1")
    public HotelDevice findByDeviceNoLock(String deviceNo);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.status=0")
    public List<HotelDevice> findAllByNoActivateLock(PageRequest request);


    public HotelDevice findByHotelIdAndRoomNo(Long hotelId,String roomNo);


    public long countByStatusAndIdIn(int status,Collection<Long> idIn);

    public long countByBuyPartnerId(Long buyPartnerId);

    public List<HotelDevice> findAllByBuyOwnerId(Long buyOwnerId);

    public List<HotelDevice> findAllByBuyPartnerId(Long buyPartnerId);

    public long countByLeaseRenterId(Long leaseRenterId);

    public long countByLeasePartnerId(Long leasePartnerId);


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.lease.status in ?1")
    public List<HotelDevice> findAllByLeaseStatusInLock(Integer[] leaseStatusIn,PageRequest request);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from HotelDevice d where d.lease.status=1 and d.lease.endTime<?1")
    public List<HotelDevice> findAllByLeaseExpireLock(Date time);

    @Modifying
    @Query("update HotelDevice d set d.province=?2 , d.city=?3 where d.hotelId=?1")
    public void updateAddress(Long hotelId,String province,String city);


    @Modifying
    @Query("update HotelDevice d set d.activeOnTime=?2,d.activeStatus=1 where d.userId=?1")
    public void activeOn(Long userId,Date time);

    @Modifying
    @Query("update HotelDevice d set d.activeOffTime=?2,d.activeStatus=0 where d.userId=?1")
    public void activeOff(Long userId,Date time);

    @Modifying
    @Query("update HotelDevice d set d.activeOnDuration=d.activeOnDuration+1 where d.userId=?1")
    public void active(Long userId);

}
