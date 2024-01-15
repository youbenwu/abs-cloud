package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Long> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from ProductCategory c where c.id=?1")
    public ProductCategory findByIdForUpdate(Long id);


    @Modifying
    @Query("update ProductCategory c set c.sort=?2  where c.id=?1")
    public void setSort(Long id, int sort);


    public ProductCategory findByCode(String code);


}
