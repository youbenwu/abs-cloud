package com.outmao.ebs.bbs.service.impl;


import com.outmao.ebs.bbs.domain.*;
import com.outmao.ebs.bbs.dto.board.BoardDTO;
import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.dto.plaint.GetPlaintListDTO;
import com.outmao.ebs.bbs.dto.plaint.PlaintDTO;
import com.outmao.ebs.bbs.dto.post.*;
import com.outmao.ebs.bbs.dto.subject.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.service.BbsService;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@Service
public class BbsServiceImpl extends BaseService implements BbsService {

	@Autowired
	private BbsDomain bbsDomain;



	@Autowired
	private SubjectDomain subjectDomain;

	@Autowired
	private PostDomain postDomain;

	@Autowired
	private CommentDomain commentDomain;


	@Autowired
    private PlaintDomain complaintDomain;

	@Override
	public Board saveBoard(BoardDTO request) {
		return bbsDomain.saveBoard(request);
	}

	@Override
	public void deleteBoardById(Long id) {
		bbsDomain.deleteBoardById(id);
	}


	@Override
	public Board getBoardByType(String type) {
		return bbsDomain.getBoardByType(type);
	}

	@Override
	public List<Board> getBoardList() {
		return bbsDomain.getBoardList();
	}




	//Subject

	@Override
	public Subject saveSubject(SubjectDTO request) {
		return subjectDomain.saveSubject(request);
	}

	@Override
	public Subject saveSubject(Long userId, Item item,int type) {
		return subjectDomain.saveSubject(userId,item,type);
	}


	@Override
	public SubjectVO getSubjectVOById(Long id) {
		return subjectDomain.getSubjectVOById(id);
	}

	@Override
	public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable) {
		return subjectDomain.getSubjectVOPage(request,pageable);
	}

	@Override
	public Page<SubjectVO> getSubjectVOPage(GetCollectionSubjectListDTO request, Pageable pageable) {
		return subjectDomain.getSubjectVOPage(request,pageable);
	}


	@Override
	public void deleteSubjectCollection(Long userId, Long subjectId) {
		subjectDomain.deleteSubjectCollection(userId,subjectId);
	}



	@Override
	public List<String> getSubjectCollectionMarkList(Long userId) {
		return subjectDomain.getSubjectCollectionMarkList(userId);
	}

	@Override
	public SubjectVote saveSubjectVote(Long userId, Long subjectId, int vote) {
		return subjectDomain.saveSubjectVote(userId,subjectId,vote);
	}

	@Override
	public void deleteSubjectVote(Long userId, Long subjectId, int vote) {
		subjectDomain.deleteSubjectVote(userId,subjectId,vote);
	}


	@Override
	public SubjectGrade saveSubjectGrade(SubjectGradeDTO request) {
		return subjectDomain.saveSubjectGrade(request);
	}



	@Override
	public Map<String, Long> getSubjectCount(GetSubjectCountDTO request) {
		return subjectDomain.getSubjectCount(request);
	}

	@Override
	public SubjectCollection saveSubjectCollection(SubjectCollectionDTO request) {
		return subjectDomain.saveSubjectCollection(request);
	}

	@Override
	public SubjectCollection modifySubjectCollection(SubjectCollectionDTO request) {
		return subjectDomain.modifySubjectCollection(request);
	}


	@Override
	public Map<String, Long> getSubjectCollectionCount(GetSubjectCollectionCountDTO request) {
		return subjectDomain.getSubjectCollectionCount(request);
	}

	@Override
	public SubjectBrowse saveSubjectBrowse(Long userId, Long subjectId) {
		return subjectDomain.saveSubjectBrowse(userId,subjectId);
	}

	@Override
	public void deleteSubjectBrowseById(Long id) {
		subjectDomain.deleteSubjectBrowseById(id);
	}

	@Override
	public void deleteSubjectBrowseList(Long userId, String itemType) {
		subjectDomain.deleteSubjectBrowseList(userId,itemType);
	}

	@Override
	public Page<SubjectBrowse> getSubjectBrowsePage(Long userId, String itemType, Pageable pageable) {
		return subjectDomain.getSubjectBrowsePage(userId,itemType,pageable);
	}


	//Post
	@Override
	public Post savePost(PostDTO request) {
		return postDomain.savePost(request);
	}


	@Override
	public PostVO getPostVOById(Long id) {
		return postDomain.getPostVOById(id);
	}

	@Override
	public Page<PostVO> getPostVOPage(GetPostListDTO request, Pageable pageable) {
		return postDomain.getPostVOPage(request,pageable);
	}

	@Override
	public Page<PostVO> getPostVOPage(GetCollectionPostListDTO request, Pageable pageable) {
		return postDomain.getPostVOPage(request,pageable);
	}

	@Override
	public PostCollection savePostCollection(Long userId, Long postId) {
		return postDomain.savePostCollection(userId,postId);
	}

	@Override
	public void deletePostCollection(Long userId, Long postId) {
		postDomain.deletePostCollection(userId,postId);
	}

	@Override
	public PostVote savePostVote(Long userId, Long postId, int vote) {
		return postDomain.savePostVote(userId,postId,vote);
	}

	@Override
	public void deletePostVote(Long userId, Long postId, int vote) {
		postDomain.deletePostVote(userId,postId,vote);
	}


	@Override
	public Post setPostTop(SetPostTopDTO request) {
		return postDomain.setPostTop(request);
	}

	@Override
	public Post setPostStatus(SetPostStatusDTO request) {
		return postDomain.setPostStatus(request);
	}



	//Comment

	@Override
	public Comment setCommentStatus(SetCommentStatusDTO request) {
		return commentDomain.setCommentStatus(request);
	}



	@Override
	public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable) {
		return commentDomain.getCommentVOPage(request,pageable);
	}


	@Override
	public Comment saveComment(CommentDTO request) {
		return commentDomain.saveComment(request);
	}



	@Override
	public CommentVote saveCommentVote(Long userId, Long commentId, int vote) {
		return commentDomain.saveCommentVote(userId,commentId,vote);
	}

	@Override
	public void deleteCommentVote(Long userId, Long commentId, int vote) {
		commentDomain.deleteCommentVote(userId,commentId,vote);
	}


	//Plaint

	@Override
	public Plaint savePlaint(PlaintDTO params) {
		return complaintDomain.savePlaint(params);
	}

	@Override
	public Plaint setPlaintStatus(Long id, int status, String statusRemark) {
		return complaintDomain.setPlaintStatus(id,status,statusRemark);
	}

	@Override
	public Page<Plaint> getPlaintPage(GetPlaintListDTO request, Pageable pageable) {
		return complaintDomain.getPlaintPage(request,pageable);
	}



}
