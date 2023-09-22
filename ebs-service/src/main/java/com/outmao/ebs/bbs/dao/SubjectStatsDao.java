package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.SubjectStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SubjectStatsDao extends JpaRepository<SubjectStats,Long> {

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.grade=?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void setGrade(Long subjectId, double grade, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.posts=s.posts+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void postsAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.likes=s.likes+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void likesAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.dislikes=s.dislikes+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void dislikesAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.favs=s.favs+?2,s.updateTime=?3 where s.subject.id=?1")
    public void favsAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.favs=s.favs+?2,s.updateTime=?3 where s.subject.id in ?1")
    public void favsAdd(List<Long> subjectIdIn, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.shares=s.shares+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void sharesAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.browses=s.browses+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void browsesAdd(Long subjectId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update SubjectStats s set s.plaints=s.plaints+?2 ,s.updateTime=?3 where s.subject.id=?1")
    public void plaintsAdd(Long subjectId, int c, Date time);


}
