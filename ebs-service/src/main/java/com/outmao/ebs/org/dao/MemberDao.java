package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MemberDao extends JpaRepository<Member,Long> {

    public Member findByOrgIdAndUserId(Long orgId, Long userId);

    public List<Member> findAllByUserId(Long userId);


    @Query("select u.user.id from Member u where u.org.id=?1")
    public List<Long> findUserIdAllByOrgId(Long orgId);

}
