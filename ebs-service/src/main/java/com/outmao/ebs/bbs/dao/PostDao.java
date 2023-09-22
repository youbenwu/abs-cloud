package com.outmao.ebs.bbs.dao;


import com.outmao.ebs.bbs.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostDao extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {

    @Query("select p.subject.id from Post p where p.id=?1")
    public Long findSubjectIdById(Long id);
    

}
