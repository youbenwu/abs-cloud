package com.outmao.ebs.movie.dao;

import com.outmao.ebs.mall.product.entity.ProductCategory;
import com.outmao.ebs.movie.entity.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;

public interface MovieCategoryDao extends JpaRepository<MovieCategory,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from MovieCategory c where c.id=?1")
    public ProductCategory findByIdLock(Long id);


    @Modifying
    @Query("update MovieCategory c set c.sort=?2  where c.id=?1")
    public void setSort(Long id, int sort);


}
