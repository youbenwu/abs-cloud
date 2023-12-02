package com.outmao.ebs.bbs.service;


import com.outmao.ebs.bbs.dto.board.BoardDTO;
import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.dto.plaint.GetPlaintListDTO;
import com.outmao.ebs.bbs.dto.plaint.PlaintDTO;
import com.outmao.ebs.bbs.dto.post.*;
import com.outmao.ebs.bbs.dto.subject.*;
import com.outmao.ebs.bbs.entity.*;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.common.vo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BbsService {

	//Board
	/*
	 *
	 * 保存版块信息
	 *
	 * */
	public Board saveBoard(BoardDTO request);
	/*
	 *
	 * 删除版块信息
	 *
	 * */
	public void deleteBoardById(Long id);
	/*
	 *
	 * 获取版块信息
	 *
	 * */
	public Board getBoardByType(String type);
	/*
	 *
	 * 获取版块列表
	 *
	 * */
	public List<Board> getBoardList();





	//Subject
	/*
	 *
	 * 保存主题信息
	 *
	 * */
	public Subject saveSubject(SubjectDTO request);
	/*
	 *
	 * 保存主题信息
	 *
	 * */
	public Subject saveSubject(Long userId, Item item,int type);
	/*
	 *
	 * 获取主题信息
	 *
	 * */
	public SubjectVO getSubjectVOById(Long id);
	/*
	 *
	 * 获取主题分页列表
	 *
	 * */
	public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable);

	/*
	 *
	 * 获取用户收藏的主题分页列表
	 *
	 * */
	public Page<SubjectVO> getSubjectVOPage(GetCollectionSubjectListDTO request, Pageable pageable);

	/*
	 *
	 * 获取用户主题数量
	 *
	 * */
	public Map<String,Long> getSubjectCount(GetSubjectCountDTO request);


	/*
	 *
	 * 主题评分
	 *
	 * */
	public SubjectGrade saveSubjectGrade(SubjectGradeDTO request);



	//SubjectCollection
	/*
	 *
	 * 收藏主题
	 *
	 * */
	public SubjectCollection saveSubjectCollection(SubjectCollectionDTO request);
	/*
	 *
	 * 修改收藏主题信息
	 *
	 * */
	public SubjectCollection modifySubjectCollection(SubjectCollectionDTO request);
	/*
	 *
	 * 删除收藏主题
	 *
	 * */
	public void deleteSubjectCollection(Long userId, Long subjectId);


	/*
	 *
	 * 获取用户收藏夹数量
	 *
	 * */
	public Map<String,Long> getSubjectCollectionCount(GetSubjectCollectionCountDTO request);

	/*
	 *
	 * 获取用户收藏夹所有标签
	 *
	 * */
	public List<String> getSubjectCollectionMarkList(Long userId);



	//SubjectVote
	/*
	 *
	 * 评价主题
	 *
	 * */
	public SubjectVote saveSubjectVote(Long userId, Long subjectId, int vote);
	/*
	 *
	 * 取消评价主题
	 *
	 * */
	public void deleteSubjectVote(Long userId, Long subjectId, int vote);


	//Post
	/*
	 *
	 * 保存帖子信息
	 *
	 * */
	public Post savePost(PostDTO request);
	/*
	 *
	 * 设置帖子在主题中是否置顶
	 *
	 * */
	public Post setPostTop(SetPostTopDTO request);
	/*
	 *
	 * 设置帖子状态
	 *
	 * */
	public Post setPostStatus(SetPostStatusDTO request);
	/*
	 *
	 * 获取帖子信息
	 *
	 * */
	public PostVO getPostVOById(Long id);
	/*
	 *
	 * 获取帖子分页列表
	 *
	 * */
	public Page<PostVO> getPostVOPage(GetPostListDTO request, Pageable pageable);
	/*
	 *
	 * 获取用户收藏的帖子分页列表
	 *
	 * */
	public Page<PostVO> getPostVOPage(GetCollectionPostListDTO request, Pageable pageable);



	//PostCollection
	/*
	 *
	 * 收藏帖子
	 *
	 * */
	public PostCollection savePostCollection(Long userId, Long postId);
	/*
	 *
	 * 取消收藏帖子
	 *
	 * */
	public void deletePostCollection(Long userId, Long postId);

	//PostVote
	/*
	 *
	 * 评价帖子
	 *
	 * */
	public PostVote savePostVote(Long userId, Long postId, int vote);
	/*
	 *
	 * 取消评价帖子
	 *
	 * */
	public void deletePostVote(Long userId, Long postId, int vote);




	//Comment
	/*
	 *
	 * 保存评论信息
	 *
	 * */
	public Comment saveComment(CommentDTO request);
	/*
	 *
	 * 设置评论状态
	 *
	 * */
	public Comment setCommentStatus(SetCommentStatusDTO request);
	/*
	 *
	 * 获取帖子评论分页列表
	 *
	 * */
	public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable);


	//CommentVote
	/*
	 *
	 * 评价评论
	 *
	 * */
	public CommentVote saveCommentVote(Long userId, Long commentId, int vote);
	/*
	 *
	 * 取消评价评论
	 *
	 * */
	public void deleteCommentVote(Long userId, Long commentId, int vote);


	public SubjectBrowse saveSubjectBrowse(Long userId, Long subjectId);

	public void deleteSubjectBrowseById(Long id);

	public void deleteSubjectBrowseList(Long userId, String itemType);

	public Page<SubjectBrowse> getSubjectBrowsePage(Long userId, String itemType, Pageable pageable);


	//Plaint
	public Plaint savePlaint(PlaintDTO request);
	public Plaint setPlaintStatus(Long id, int status, String statusRemark);
	public Page<Plaint> getPlaintPage(GetPlaintListDTO request, Pageable pageable);


}
