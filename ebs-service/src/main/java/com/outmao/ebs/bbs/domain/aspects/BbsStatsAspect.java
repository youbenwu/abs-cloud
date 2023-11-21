package com.outmao.ebs.bbs.domain.aspects;


import com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd;
import com.outmao.ebs.bbs.common.data.SubjectItem;
import com.outmao.ebs.bbs.dao.*;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.post.PostDTO;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/*
 *
 * 数据统计
 * 
 * */

@Aspect
@Component
public class BbsStatsAspect {

	@Autowired
    private BoardStatsDao boardStatsDao;

	@Autowired
    private SubjectStatsDao subjectStatsDao;

	@Autowired
    private PostStatsDao postStatsDao;

	@Autowired
    private CommentStatsDao commentStatsDao;

	@Autowired
	private SubjectGradeDao subjectGradeDao;

	@Autowired
	private SubjectDomain subjectDomain;



	@Pointcut("@annotation(com.outmao.ebs.bbs.common.annotation.SubjectBrowsesAdd)")
	public void SubjectBrowsesAdd() {
	}

	@Transactional
	@AfterReturning(returning = "item",value = "SubjectBrowsesAdd() && @annotation(add)")
	public void afterSubjectBrowsesAdd(JoinPoint jp, SubjectItem item, SubjectBrowsesAdd add) {
		if(item==null)
			return;
		subjectStatsDao.browsesAdd(item.getSubjectId(),add.add(),new Date());
		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();
			subjectDomain.saveSubjectBrowse(user.getId(),item.getSubjectId());
		}
	}


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.saveSubjectGrade(..))")
	public void saveSubjectGrade() { }


	@Transactional
	@AfterReturning(returning = "grade", pointcut = "saveSubjectGrade()")
	public void afterSaveSubjectGrade(JoinPoint jp, SubjectGrade grade) {
        if(grade.getType()==0){
        	double g=subjectGradeDao.findAvgGradeBySubjectId(grade.getSubject().getId());
            subjectStatsDao.setGrade(grade.getSubject().getId(),g,new Date());
		}
	}


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.getSubjectVO*(..))")
	public void getSubjectVO() {
	}

	@Transactional
	@AfterReturning(returning = "vo", pointcut = "getSubjectVO()")
	public void afterGetSubjectVO(JoinPoint jp, SubjectVO vo) {
		if (vo == null)
			return;
		// 浏览加1
		subjectStatsDao.browsesAdd(vo.getId(), 1,new Date());

		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();
			subjectDomain.saveSubjectBrowse(user.getId(),vo.getId());
		}

	}




	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.saveSubjectCollection(..))")
	public void saveSubjectCollection() {

	}

	@Transactional
	@AfterReturning(returning = "c", pointcut = "saveSubjectCollection()")
	public void afterSaveSubjectCollection(JoinPoint jp, SubjectCollection c) {
		subjectStatsDao.favsAdd(c.getSubject().getId(), 1,c.getCreateTime());
	}


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.deleteSubjectCollection(..))")
	public void deleteSubjectCollection() {

	}

	@Transactional
	@AfterReturning(pointcut = "deleteSubjectCollection()")
	public void afterDeleteSubjectCollection(JoinPoint jp) {
		subjectStatsDao.favsAdd((Long) jp.getArgs()[1], -1,new Date());
	}



	// ---------

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.savePostCollection(..))")
	public void savePostCollection() {

	}

	@Transactional
	@AfterReturning(returning = "c", pointcut = "savePostCollection()")
	public void afterSavePostCollection(JoinPoint jp, PostCollection c) {
		postStatsDao.favsAdd(c.getPost().getId(), 1,c.getCreateTime());
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.deletePostCollection(..))")
	public void deletePostCollection() {

	}

	@AfterReturning(pointcut = "deletePostCollection()")
	public void afterDeletePostCollection(JoinPoint jp) {
		postStatsDao.favsAdd((Long) jp.getArgs()[1], -1,new Date());
	}


	//--


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.getPostVO*(..))")
	public void getPostVO() {
	}

	@Transactional
	@AfterReturning(returning = "vo", pointcut = "getPostVO()")
	public void afterGetPostVO(JoinPoint jp, PostVO vo) {
		if (vo == null)
			return;
		// 浏览加1
		postStatsDao.browsesAdd(vo.getId(), 1, new Date());

	}


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.savePost(..))")
	public void savePost() {
	}

	@Transactional
	@AfterReturning(returning = "p", pointcut = "savePost()")
	public void afterSavePost(JoinPoint jp, Post p) {
		PostDTO params=(PostDTO) jp.getArgs()[0];
		if (params.getId() == null) {
			subjectStatsDao.postsAdd(p.getSubject().getId(), 1, p.getCreateTime());
		}
	}



	/*
	 *
	 * 保存评论后统计数据
	 *
	 * */
	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.CommentDomain.saveComment(..))")
	public void saveComment() { }


	@Transactional
	@AfterReturning(returning = "c", pointcut = "saveComment()")
	public void afterSaveComment(JoinPoint jp, Comment c) {
		CommentDTO request=(CommentDTO) jp.getArgs()[0];
		if (request.getId() == null) {
			//新增评论，帖子回复数加加
			postStatsDao.replysAdd(c.getPost().getId(), 1, c.getCreateTime());
			if(c.getTo()!=null){
				commentStatsDao.replysAdd(c.getTo().getId(),1);
			}
		}
	}



	//投票统计

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.saveSubjectVote(..))")
	public void saveSubjectVote() {
	}

	@Transactional
	@AfterReturning(pointcut = "saveSubjectVote()")
	public void afterSaveSubjectVote(JoinPoint jp) {
		int vote = (int) jp.getArgs()[2];
		if (vote == 0) {
			subjectStatsDao.likesAdd((Long) jp.getArgs()[1], 1, new Date());
		} else if (vote == 1) {
			subjectStatsDao.dislikesAdd((Long) jp.getArgs()[1], 1, new Date());
		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.deleteSubjectVote(..))")
	public void deleteSubjectVote() {
	}

	@Transactional
	@AfterReturning(pointcut = "deleteSubjectVote()")
	public void afterDeleteSubjectVote(JoinPoint jp) {
		int vote = (int) jp.getArgs()[2];
		if (vote == 0) {
			subjectStatsDao.likesAdd((Long) jp.getArgs()[1], -1, new Date());
		} else if (vote == 1) {
			subjectStatsDao.dislikesAdd((Long) jp.getArgs()[1], -1, new Date());
		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.savePostVote(..))")
	public void savePostVote() {
	}

	@Transactional
	@AfterReturning(pointcut = "savePostVote()")
	public void afterSavePostVote(JoinPoint jp) {
		int vote = (int) jp.getArgs()[2];
		if (vote == 0) {
			postStatsDao.likesAdd((Long) jp.getArgs()[1], 1, new Date());
		} else if (vote == 1) {
			postStatsDao.dislikesAdd((Long) jp.getArgs()[1], 1, new Date());
		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.deletePostVote(..))")
	public void deletePostVote() {
	}

	@Transactional
	@AfterReturning(pointcut = "deletePostVote()")
	public void afterDeletePostVote(JoinPoint jp) {
		int vote = (int) jp.getArgs()[2];
		if (vote == 0) {
			postStatsDao.likesAdd((Long) jp.getArgs()[1], -1, new Date());
		} else if (vote == 1) {
			postStatsDao.dislikesAdd((Long) jp.getArgs()[1], -1, new Date());
		}
	}



	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.CommentDomain.saveCommentVote(..))")
	public void saveCommentVote() { }

	@Transactional
	@AfterReturning(pointcut = "saveCommentVote()")
	public void afterSaveCommentVote(JoinPoint jp) {
		int vote = (int) jp.getArgs()[2];
		if (vote == 0) {
			commentStatsDao.likesAdd((Long) jp.getArgs()[1], 1);
		} else if (vote == 1) {
			commentStatsDao.dislikesAdd((Long) jp.getArgs()[1], 1);
		}
	}


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.CommentDomain.deleteCommentVote(..))")
	public void deleteCommentVote() { }

	@Transactional
	@AfterReturning(pointcut = "deleteCommentVote()")
	public void afterDeleteCommentVote(JoinPoint jp) {
		int replyType = (int) jp.getArgs()[2];
		if (replyType == 0) {
			commentStatsDao.likesAdd((Long) jp.getArgs()[1], -1);
		} else if (replyType == 1) {
			commentStatsDao.dislikesAdd((Long) jp.getArgs()[1], -1);
		}
	}

	// 抗诉统计

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PlaintDomain.savePlaint(..))")
	public void saveComplaint() {
	}

	@Transactional
	@AfterReturning(pointcut = "saveComplaint()",returning = "c")
	public void afterSaveComplaint(JoinPoint jp, Plaint c) {
		if(c.getTargetType().equals("Subject")){
           subjectStatsDao.plaintsAdd(c.getTargetId(),1,new Date());
		}else if(c.getTargetType().equals("Post")){
           postStatsDao.plaintsAdd(c.getTargetId(),1);
		}else if(c.getTargetType().equals("Comment")){
           commentStatsDao.plaintsAdd(c.getTargetId(),1);
		}
	}


}
