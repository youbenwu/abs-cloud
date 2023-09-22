package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface ArticleCategoryDao extends JpaRepository<ArticleCategory,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select c from ArticleCategory c where c.id=?1")
    public ArticleCategory findByIdForUpdate(Long id);


}
