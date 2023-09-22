package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.CommentVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentVoteDao extends JpaRepository<CommentVote, Long> {

	public CommentVote findByUserIdAndCommentId(Long userId, Long commentId);

	public List<CommentVote> findAllByUserIdAndCommentIdIn(Long userId, List<Long> commentIdIn);


}
