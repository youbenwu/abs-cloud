package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.StoreSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface StoreSkuDao extends JpaRepository<StoreSku,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public StoreSku findByStoreIdAndSkuId(Long storeId, Long skuId);

    public List<StoreSku> findAllByProductId(Long productId);

}
