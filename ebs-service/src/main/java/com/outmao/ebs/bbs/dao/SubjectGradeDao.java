package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.SubjectGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectGradeDao extends JpaRepository<SubjectGrade,Long> {

    public SubjectGrade findByTypeAndUserIdAndSubjectId(int type, Long userId, Long subjectId);

    @Query("select AVG(s.grade) from SubjectGrade s where s.subject.id=?1")
    public double findAvgGradeBySubjectId(Long subjectId);

    @Query("select AVG(s.grade) from SubjectGrade s where s.type=?1 and s.subject.id=?2")
    public double findAvgGradeByTypeAndSubjectId(int type, Long subjectId);


}
