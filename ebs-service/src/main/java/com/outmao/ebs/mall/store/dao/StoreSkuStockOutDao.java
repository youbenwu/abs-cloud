package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.StoreSkuStockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface StoreSkuStockOutDao extends JpaRepository<StoreSkuStockOut,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from StoreSkuStockOut o where o.id=?1")
    public StoreSkuStockOut findByIdForUpdate(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from StoreSkuStockOut o where o.orderId=?1")
    public StoreSkuStockOut findByOrderIdForUpdate(Long orderId);


}
