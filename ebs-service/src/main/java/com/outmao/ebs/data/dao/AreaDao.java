package com.outmao.ebs.data.dao;

import com.outmao.ebs.data.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface AreaDao extends JpaRepository<Area,Long> {


    public Area findByParentIdAndName(Long parentId, String name);

    public Area findByCode(String code);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select a from Area a where a.id=?1")
    public Area findByIdLock(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select a from Area a where a.parent.id=?1 and a.name=?2")
    public Area findByParentIdAndNameLock(Long parentId, String name);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select a from Area a where a.code=?1")
    public Area findByCodeLock(String code);

}
