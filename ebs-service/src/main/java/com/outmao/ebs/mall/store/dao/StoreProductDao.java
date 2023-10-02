package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreProductDao extends JpaRepository<StoreProduct,Long> {

    public void deleteAllByProductId(Long productId);

    public void deleteAllByStoreId(Long storeId);

    public StoreProduct findByStoreIdAndProductId(Long storeId, Long productId);

    @Query("select e.store.title from StoreProduct e where e.product.id=?1")
    public List<String> findAllStoreTitleByProductId(Long productId);

}
