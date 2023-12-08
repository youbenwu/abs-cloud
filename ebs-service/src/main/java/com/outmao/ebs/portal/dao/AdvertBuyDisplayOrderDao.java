package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertBuyDisplayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface AdvertBuyDisplayOrderDao extends JpaRepository<AdvertBuyDisplayOrder,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from AdvertBuyDisplayOrder o where o.orderNo=?1")
    public AdvertBuyDisplayOrder findByOrderNoLock(String orderNo);

}
