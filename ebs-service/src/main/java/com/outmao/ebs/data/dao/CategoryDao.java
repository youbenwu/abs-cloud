package com.outmao.ebs.data.dao;

import com.outmao.ebs.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface CategoryDao extends JpaRepository<Category,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from Category c where c.id=?1")
    public Category findByIdForUpdate(Long id);

}
