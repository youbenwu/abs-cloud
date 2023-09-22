package com.outmao.ebs.bbs.dao;

import com.outmao.ebs.bbs.entity.PostCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostCollectionDao  extends JpaRepository<PostCollection, Long> {

	public void deleteByUserIdAndPostId(Long userId, Long postId);

	public PostCollection findByUserIdAndPostId(Long userId, Long postId);

	@Query("select e.post.id from PostCollection e where e.user.id=?1 and e.post.id in ?2")
	public Collection<Long> findAllPostIdByUserIdAndPostIdIn(Long userId, List<Long> postIdIn);


}
