package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.JobMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobMemberDao extends JpaRepository<JobMember,Long> {

    public JobMember findByJobIdAndMemberId(Long jobId, Long memberId);

    @Query("select e.member.id from JobMember  e where e.job.id=?1")
    public List<Long> findAllMemberIdByJobId(Long jobId);

    @Modifying
    public void deleteAllByJobId(Long jobId);

    @Modifying
    public void deleteAllByMemberId(Long memberId);

}
