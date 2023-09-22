package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.PostStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface PostStatsDao extends JpaRepository<PostStats,Long> {

    @Transactional
    @Modifying
    @Query("update PostStats p set p.updateTime=?3 , p.replys=p.replys+?2  where p.post.id=?1")
    public void replysAdd(Long postId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update PostStats p set p.updateTime=?3 , p.browses=p.browses+?2 where p.post.id=?1")
    public void browsesAdd(Long postId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update PostStats p set p.updateTime=?3 , p.favs=p.favs+?2  where p.post.id=?1")
    public void favsAdd(Long postId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update PostStats p set p.updateTime=?3 , p.likes=p.likes+?2  where p.post.id=?1")
    public void likesAdd(Long postId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update PostStats p set p.updateTime=?3 , p.dislikes=p.dislikes+?2  where p.post.id=?1")
    public void dislikesAdd(Long postId, int c, Date time);

    @Transactional
    @Modifying
    @Query("update PostStats p set p.plaints=p.plaints+?2  where p.post.id=?1")
    public void plaintsAdd(Long postId, int c);

}
