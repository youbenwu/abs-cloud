package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface OrgDao extends JpaRepository<Org,Long> {

    public Org findByType(int type);

    public Org findByTargetId(Long targetId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from Org o where o.id=?1")
    public Org findByIdForUpdate(Long id);

    public List<Org> findAllByIdIn(Collection<Long> idIn);

    @Query("select o.id from Org o where o.targetId=?1")
    public Long findIdByTargetId(Long targetId);

}
