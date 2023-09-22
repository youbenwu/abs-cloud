package com.outmao.ebs.bbs.domain.impl;


import com.outmao.ebs.bbs.dao.CommentDao;
import com.outmao.ebs.bbs.dao.CommentVoteDao;
import com.outmao.ebs.bbs.dao.PostDao;
import com.outmao.ebs.bbs.dao.SubjectDao;
import com.outmao.ebs.bbs.domain.CommentDomain;
import com.outmao.ebs.bbs.domain.conver.CommentVOConver;
import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.entity.Comment;
import com.outmao.ebs.bbs.entity.CommentStats;
import com.outmao.ebs.bbs.entity.CommentVote;
import com.outmao.ebs.bbs.entity.QComment;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class CommentDomainImpl  extends BaseDomain implements CommentDomain {


    @Autowired
    private UserDao userDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentVoteDao commentVoteDao;

    private CommentVOConver commentVOConver=new CommentVOConver();

    @Transactional
    @Override
    public Comment saveComment(CommentDTO request) {
        Comment c = request.getId()==null?null:commentDao.getOne(request.getId());
        if (c == null) {
            c = new Comment();
            c.setUser(userDao.getOne(request.getUserId()));
            c.setPost(postDao.getOne(request.getPostId()));
            c.setSubject(subjectDao.getOne(postDao.findSubjectIdById(request.getPostId())));
            if (request.getToId() != null) {
                c.setTo(commentDao.getOne(request.getToId()));
            }
            c.setCreateTime(new Date());
            CommentStats stats=new CommentStats();
            stats.setComment(c);
            c.setStats(stats);
        }
        BeanUtils.copyProperties(request,c);
        c.setUpdateTime(new Date());
        commentDao.save(c);
        return c;
    }


    @Transactional
    @Override
    public Comment setCommentStatus(SetCommentStatusDTO request) {
        Comment c=commentDao.getOne(request.getId());
        c.setStatus(request.getStatus());
        commentDao.save(c);
        return c;
    }

    @SetSimpleUser
    @Override
    public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable) {
        QComment e = QComment.comment;
        Predicate p=null;
        if(request.getPostId()!=null){
            p=e.post.id.eq(request.getPostId()).and(p);
        }
        if(request.getToId()!=null){
            p=e.to.id.eq(request.getToId()).and(p);
        }
        return queryPage(e,p,commentVOConver,pageable);
    }


    @Transactional
    @Override
    public CommentVote saveCommentVote(Long userId, Long commentId, int vote) {
        CommentVote r = commentVoteDao.findByUserIdAndCommentId(userId, commentId);
        if (r != null) {
            throw new BusinessException("已评价");
        }
        r = new CommentVote();
        r.setUser(userDao.getOne(userId));
        r.setComment(commentDao.getOne(commentId));
        r.setVote(vote);
        r.setCreateTime(new Date());
        commentVoteDao.save(r);
        return r;
    }

    @Transactional
    @Override
    public void deleteCommentVote(Long userId, Long commentId, int vote) {
        CommentVote r = commentVoteDao.findByUserIdAndCommentId(userId, commentId);
        if (r == null || r.getVote() != vote) {
            throw new BusinessException("出错了");
        }
        commentVoteDao.delete(r);
    }

}
