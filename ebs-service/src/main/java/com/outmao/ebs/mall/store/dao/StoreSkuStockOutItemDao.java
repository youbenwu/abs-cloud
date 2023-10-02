package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.StoreSkuStockOutItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreSkuStockOutItemDao extends JpaRepository<StoreSkuStockOutItem,Long> {


    public List<StoreSkuStockOutItem> findAllByStockOutId(Long stockOutId);

}
