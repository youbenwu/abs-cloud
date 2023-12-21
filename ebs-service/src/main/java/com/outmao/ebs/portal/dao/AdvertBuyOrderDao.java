package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertBuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface AdvertBuyOrderDao extends JpaRepository<AdvertBuyOrder,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from AdvertBuyOrder o where o.orderNo=?1")
    public AdvertBuyOrder findByOrderNoLock(String orderNo);

    @Query("select o.advertId from AdvertBuyOrder o where o.orderNo in ?1")
    public List<Long> findAllAdvertIdByOrderNoIn(Collection<String> orderNoIn);

    public List<AdvertBuyOrder> findAllByOrderNoIn(Collection<String> orderNoIn);


}
