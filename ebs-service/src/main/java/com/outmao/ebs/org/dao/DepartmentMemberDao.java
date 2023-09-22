package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentMemberDao extends JpaRepository<DepartmentMember,Long> {

    public DepartmentMember findByDepartmentIdAndMemberId(Long departmentId, Long memberId);

    @Query("select e.member.id from DepartmentMember e  where e.department.id=?1")
    public List<Long> findAllMemberIdByDepartmentId(Long departmentId);

    public void deleteAllByDepartmentId(Long departmentId);

    public void deleteAllByMemberId(Long memberId);


}
