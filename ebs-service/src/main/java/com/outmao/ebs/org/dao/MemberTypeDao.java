package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTypeDao extends JpaRepository<MemberType,Long> {

    public MemberType findByMemberIdAndType(Long memberId,int type);

    public void deleteAllByMemberId(Long memberId);

}
