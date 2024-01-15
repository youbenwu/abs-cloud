package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.List;

public interface MemberDao extends JpaRepository<Member,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select m from Member m where m.id=?1")
    public Member findByIdLock(Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select m from Member m where m.org.id=?1 and m.user.id=?2")
    public Member findByOrgIdAndUserIdLock(Long orgId, Long userId);

    public Member findByOrgIdAndUserId(Long orgId, Long userId);

    public List<Member> findAllByUserId(Long userId);


    @Query("select u.user.id from Member u where u.org.id=?1")
    public List<Long> findUserIdAllByOrgId(Long orgId);

}
