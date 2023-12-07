package com.outmao.ebs.bbs.domain.impl;


import com.outmao.ebs.bbs.dao.PostCollectionDao;
import com.outmao.ebs.bbs.dao.PostDao;
import com.outmao.ebs.bbs.dao.PostVoteDao;
import com.outmao.ebs.bbs.dao.SubjectDao;
import com.outmao.ebs.bbs.domain.PostDomain;
import com.outmao.ebs.bbs.domain.conver.PostVOConver;
import com.outmao.ebs.bbs.dto.post.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class PostDomainImpl extends BaseDomain implements PostDomain {


    @Autowired
    private UserDao userDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostVoteDao postVoteDao;

    @Autowired
    private PostCollectionDao postCollectionDao;

    private PostVOConver postVOConver=new PostVOConver();


    @Transactional
    @Override
    public Post savePost(PostDTO request) {
        Post post = request.getId()==null?null:postDao.getOne(request.getId());
        if (post == null) {
            post = new Post();
            post.setUser(userDao.getOne(request.getUserId()));
            post.setSubject(subjectDao.getOne(request.getSubjectId()));
            post.setCreateTime(new Date());
            PostStats stats=new PostStats();
            stats.setPost(post);
            post.setStats(stats);
        }
        BeanUtils.copyProperties(request,post);
        post.setUpdateTime(new Date());
        postDao.save(post);
        return post;
    }

    @Transactional
    @Override
    public Post setPostTop(SetPostTopDTO request) {
        Post post=postDao.getOne(request.getId());
        if(!request.getUserId().equals(subjectDao.findUserIdById(post.getUser().getId()))){
            throw new BusinessException("无权操作");
        }
        post.setTop(request.isTop());
        postDao.save(post);
        return post;
    }

    @Transactional
    @Override
    public Post setPostStatus(SetPostStatusDTO request) {
        Post post=postDao.getOne(request.getId());
        post.setStatus(request.getStatus());
        postDao.save(post);
        return post;
    }

    @SetSimpleUser
    @Override
    public PostVO getPostVOById(Long id) {
        QPost e = QPost.post;
        return queryOne(e,e.id.eq(id),postVOConver);
    }

    @SetSimpleUser
    @Override
    public Page<PostVO> getPostVOPage(GetPostListDTO request, Pageable pageable) {
        QPost e = QPost.post;
        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }
        if(request.getSubjectId()!=null){
            p=e.subject.id.eq(request.getSubjectId()).and(p);
        }
        if(request.getItemId()!=null){
            p=e.item.id.eq(request.getItemId()).and(p);
        }
        if(request.getItemType()!=null){
            p=e.item.type.eq(request.getItemType()).and(p);
        }
        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }
        return queryPage(e,p,postVOConver,pageable);
    }

    @SetSimpleUser
    @Override
    public Page<PostVO> getPostVOPage(GetCollectionPostListDTO request, Pageable pageable) {
        QPost e = QPost.post;
        QPostCollection c=QPostCollection.postCollection;
        JPAQuery<Tuple> query=QF.select(postVOConver.select(e)).from(e,c)
                .where(e.id.eq(c.post.id).and(c.user.id.eq(request.getUserId())));
        Page<PostVO> page=queryPage(e,query,postVOConver,pageable);
        for(PostVO vo : page.getContent()){
            vo.setFav(true);
        }
        return page;
    }


    @Transactional
    @Override
    public PostCollection savePostCollection(Long userId, Long postId) {
        PostCollection c = postCollectionDao.findByUserIdAndPostId(userId, postId);
        if (c != null) {
            throw new BusinessException("已收藏");
        }
        c = new PostCollection();
        c.setUser(userDao.getOne(userId));
        c.setPost(postDao.getOne(postId));
        c.setCreateTime(new Date());
        postCollectionDao.save(c);
        return c;
    }

    @Transactional
    @Override
    public void deletePostCollection(Long userId, Long postId) {
        PostCollection c = postCollectionDao.findByUserIdAndPostId(userId, postId);
        if (c == null) {
            throw new BusinessException("收藏不存在");
        }
        postCollectionDao.delete(c);
    }

    @Transactional
    @Override
    public PostVote savePostVote(Long userId, Long postId, int vote) {
        PostVote r = postVoteDao.findByUserIdAndPostId(userId, postId);
        if (r != null) {
            throw new BusinessException("已评价");
        }
        r = new PostVote();
        r.setUser(userDao.getOne(userId));
        r.setPost(postDao.getOne(postId));
        r.setVote(vote);
        r.setCreateTime(new Date());
        postVoteDao.save(r);

        return r;
    }

    @Transactional
    @Override
    public void deletePostVote(Long userId, Long postId, int vote) {
        PostVote r = postVoteDao.findByUserIdAndPostId(userId, postId);
        if (r == null || r.getVote() != vote) {
            throw new BusinessException("出错了");
        }
        postVoteDao.delete(r);
    }


}
