package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
