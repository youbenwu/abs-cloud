package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.BoardStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardStatsDao extends JpaRepository<BoardStats,Long> {


    @Transactional
    @Modifying
    @Query("update BoardStats s set s.subjects=s.subjects+?2  where s.board.id=?1")
    public void subjectsAdd(Long boardId, int c);


}
