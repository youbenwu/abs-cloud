package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertBuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface AdvertBuyOrderDao extends JpaRepository<AdvertBuyOrder,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from AdvertBuyOrder o where o.orderNo=?1")
    public AdvertBuyOrder findByOrderNoLock(String orderNo);


}
