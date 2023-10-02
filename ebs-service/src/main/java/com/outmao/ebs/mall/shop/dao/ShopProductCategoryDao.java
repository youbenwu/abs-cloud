package com.outmao.ebs.mall.shop.dao;

import com.outmao.ebs.mall.shop.entity.ShopProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface ShopProductCategoryDao extends JpaRepository<ShopProductCategory,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from ShopProductCategory c where c.id=?1")
    public ShopProductCategory findByIdForUpdate(Long id);

}
