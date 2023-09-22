package com.outmao.ebs.bbs.dao;



import com.outmao.ebs.bbs.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDao extends JpaRepository<Board, Long> {


    public Board findByType(String type);



}
