package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;

public interface AdvertDao extends JpaRepository<Advert,Long> , QuerydslPredicateExecutor<Advert> {


    @Modifying
    @Query("update Advert a set a.pv=a.pv+1 where a.id=?1")
    public void pv(Long id);

    @Modifying
    @Query("update Advert a set a.uv=a.uv+1 where a.id=?1")
    public void uv(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select a from Advert  a where a.id=?1")
    public Advert findByIdForUpdate(Long id);

    @Transactional
    @Modifying
    @Query("update Advert p set p.sort=?2 where p.id=?1")
    public void setSort(Long id, int sort);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select d from Advert d where d.buyDisplay.endTime<?1")
    public List<Advert> findAllByBuyDisplayExpireLock(Date time);

}
