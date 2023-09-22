package com.outmao.ebs.bbs.domain.aspects;


import com.outmao.ebs.bbs.dao.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * 过滤用户数据
 * 
 * */

@Aspect
@Component
public class BbsDomainFilterAspect {


	@Autowired
	private SubjectVoteDao subjectVoteDao;

	@Autowired
	private SubjectCollectionDao subjectCollectionDao;

	@Autowired
	private PostVoteDao postVoteDao;

	@Autowired
	private PostCollectionDao postCollectionDao;

	@Autowired
	private CommentVoteDao commentVoteDao;


	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.getSubjectVOPage*(..))")
	public void getSubjectVOPage() {
	}

	@AfterReturning(returning = "page", pointcut = "getSubjectVOPage()")
	public void afterGetSubjectVOPage(JoinPoint jp, Page<SubjectVO> page) {
		if (page.getContent().isEmpty())
			return;
		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			List<Long> ids = new ArrayList<>();
			for (SubjectVO vo : page.getContent()) {
				ids.add(vo.getId());
			}

			// 评价数据
			List<SubjectVote> replys = subjectVoteDao.findAllByUserIdAndSubjectIdIn(user.getId(), ids);
			for (SubjectVote reply : replys) {
				SubjectVO vo = page.getContent().get(ids.indexOf(reply.getSubject().getId()));
				if (reply.getVote() == 0) {
					vo.setLike(true);
				} else if (reply.getVote() == 1) {
					vo.setDislike(true);
				}
			}

			// 收藏数据
			if (page.getContent().get(0).getFav()==null) {
				Set<Long> likes = subjectCollectionDao.findAllSubjectIdByUserIdAndSubjectIdIn(user.getId(), ids);
				for (SubjectVO vo : page.getContent()) {
					if (likes.contains(vo.getId())) {
						vo.setFav(true);
					}
				}
			}

		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.SubjectDomain.getSubjectVO*(..))")
	public void getSubjectVO() {
	}

	@AfterReturning(returning = "vo", pointcut = "getSubjectVO()")
	public void afterGetSubjectVO(JoinPoint jp, SubjectVO vo) {
		if (vo == null)
			return;

		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			// 评价数据
			SubjectVote reply = subjectVoteDao.findByUserIdAndSubjectId(user.getId(), vo.getId());
			if (reply != null) {
				if (reply.getVote() == 0) {
					vo.setLike(true);
				} else if (reply.getVote() == 1) {
					vo.setDislike(true);
				}
			}

			// 收藏数据
			SubjectCollection c = subjectCollectionDao.findByUserIdAndSubjectId(user.getId(), vo.getId());
			vo.setFav(c != null);

		}
	}



	
	
	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.CommentDomain.getCommentVOPage*(..))")
	public void getCommentVOPage() {
	}

	@AfterReturning(returning = "page", pointcut = "getCommentVOPage()")
	public void afterGetCommentVOPage(JoinPoint jp, Page<CommentVO> page) {
		if (page.getContent().isEmpty())
			return;
		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			List<Long> ids =  page.getContent().stream().map(t->t.getId()).collect(Collectors.toList());

			// 评价数据
			List<CommentVote> replys = commentVoteDao.findAllByUserIdAndCommentIdIn(user.getId(), ids);
			for (CommentVote reply : replys) {
				CommentVO vo = page.getContent().get(ids.indexOf(reply.getComment().getId()));
				if (reply.getVote() == 0) {
					vo.setLike(true);
				} else if (reply.getVote() == 1) {
					vo.setDislike(true);
				}
			}

		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.CommentDomain.getCommentVO*(..))")
	public void getCommentVO() {
	}

	@AfterReturning(returning = "vo", pointcut = "getCommentVO()")
	public void afterGetCommentVO(JoinPoint jp, CommentVO vo) {
		if (vo == null)
			return;

		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			// 评价数据
			CommentVote vote = commentVoteDao.findByUserIdAndCommentId(user.getId(), vo.getId());
			if (vote != null) {
				if (vote.getVote() == 0) {
					vo.setLike(true);
				} else if (vote.getVote() == 1) {
					vo.setDislike(true);
				}
			}

		}
	}


	
	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.getPostVOPage*(..))")
	public void getPostVOPage() {
	}

	@AfterReturning(returning = "page", pointcut = "getPostVOPage()")
	public void afterGetPostVOPage(JoinPoint jp, Page<PostVO> page) {
		if (page.getContent().isEmpty())
			return;
		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			List<Long> ids = page.getContent().stream().map(t->t.getId()).collect(Collectors.toList());

			// 评价数据
			List<PostVote> replys = postVoteDao.findAllByUserIdAndPostIdIn(user.getId(), ids);
			for (PostVote reply : replys) {
				PostVO vo = page.getContent().get(ids.indexOf(reply.getPost().getId()));
				if (reply.getVote() == 0) {
					vo.setLike(true);
				} else if (reply.getVote() == 1) {
					vo.setDislike(true);
				}
			}

			// 收藏数据
			if (page.getContent().get(0).getFav()==null) {
				Collection<Long> favs = postCollectionDao.findAllPostIdByUserIdAndPostIdIn(user.getId(), ids);
				for (PostVO vo : page.getContent()) {
					vo.setFav(favs.contains(vo.getId()));
				}
			}

		}
	}

	@Pointcut("execution(public * com.outmao.ebs.bbs.domain.PostDomain.getPostVO*(..))")
	public void getPostVO() {
	}

	@AfterReturning(returning = "vo", pointcut = "getPostVO()")
	public void afterGetPostVO(JoinPoint jp, PostVO vo) {
		if (vo == null)
			return;

		if (SecurityUtil.isAuthenticated()) {
			SecurityUser user = SecurityUtil.currentUser();

			// 评价数据
			PostVote vote = postVoteDao.findByUserIdAndPostId(user.getId(), vo.getId());
			if (vote != null) {
				if (vote.getVote() == 0) {
					vo.setLike(true);
				} else if (vote.getVote() == 1) {
					vo.setDislike(true);
				}
			}

			// 收藏数据
			PostCollection c = postCollectionDao.findByUserIdAndPostId(user.getId(), vo.getId());
			vo.setFav(c != null);

		}
	}



}
