package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;

public interface AdvertDao extends JpaRepository<Advert,Long> , QuerydslPredicateExecutor<Advert> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select a from Advert  a where a.id=?1")
    public Advert findByIdForUpdate(Long id);


}
