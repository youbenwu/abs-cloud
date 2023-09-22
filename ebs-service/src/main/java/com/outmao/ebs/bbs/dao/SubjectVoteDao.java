package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.SubjectVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectVoteDao extends JpaRepository<SubjectVote, Long> {

	public SubjectVote findByUserIdAndSubjectId(Long userId, Long subjectId);

	public List<SubjectVote> findAllByUserIdAndSubjectIdIn(Long userId, List<Long> subjectIdIn);

	

}
