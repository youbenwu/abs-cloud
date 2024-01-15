package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.MemberVip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface MemberVipDao extends JpaRepository<MemberVip,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select v from MemberVip v where v.memberId=?1")
    public MemberVip findByMemberIdLock(Long memberId);

    public MemberVip findByMemberId(Long memberId);

    public void deleteByMemberId(Long memberId);


}
