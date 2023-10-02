package com.outmao.ebs.org.dao;


import com.outmao.ebs.org.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleDao extends JpaRepository<MemberRole,Long> {




	public MemberRole findByMemberIdAndRoleId(Long memberId, Long roleId);

	public List<MemberRole> findAllByMemberId(Long memberId);

	public List<MemberRole> findAllByRoleId(Long roleId);

	public void deleteAllByMemberId(Long memberId);

	public void deleteAllByRoleId(Long roleId);

	public void deleteAllByMemberIdAndRoleIdNotIn(Long memberId,List<Long> roleIdNotIn);

}
