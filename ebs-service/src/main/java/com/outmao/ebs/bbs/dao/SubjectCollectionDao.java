package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.SubjectCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SubjectCollectionDao extends JpaRepository<SubjectCollection, Long> {


	public SubjectCollection findByUserIdAndSubjectId(Long userId, Long subjectId);


	@Query("select e.subject.id from SubjectCollection e where e.user.id=?1 and e.subject.id in ?2")
	public Set<Long> findAllSubjectIdByUserIdAndSubjectIdIn(Long userId, List<Long> subjectIdIn);


	public List<SubjectCollection> findAllByUserIdAndSubjectIdIn(Long userId, List<Long> subjectIdIn);



}
