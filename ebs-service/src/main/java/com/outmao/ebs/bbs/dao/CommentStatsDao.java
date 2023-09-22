package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.CommentStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentStatsDao extends JpaRepository<CommentStats,Long> {

    @Transactional
    @Modifying
    @Query("update CommentStats c set c.replys=c.replys+?2  where c.comment.id=?1")
    public void replysAdd(Long commentId, int c);

    @Transactional
    @Modifying
    @Query("update CommentStats c set c.likes=c.likes+?2  where c.comment.id=?1")
    public void likesAdd(Long commentId, int c);

    @Transactional
    @Modifying
    @Query("update CommentStats c set c.dislikes=c.dislikes+?2  where c.comment.id=?1")
    public void dislikesAdd(Long commentId, int c);

    @Transactional
    @Modifying
    @Query("update CommentStats c set c.plaints=c.plaints+?2  where c.comment.id=?1")
    public void plaintsAdd(Long commentId, int c);

}
