package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostVoteDao extends JpaRepository<PostVote, Long> {

	public PostVote findByUserIdAndPostId(Long userId, Long postId);

	public List<PostVote> findAllByUserIdAndPostIdIn(Long userId, List<Long> postIdIn);


}
