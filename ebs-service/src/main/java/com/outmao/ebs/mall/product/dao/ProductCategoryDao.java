package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface ProductCategoryDao extends JpaRepository<ProductCategory,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from ProductCategory c where c.id=?1")
    public ProductCategory findByIdForUpdate(Long id);

}
