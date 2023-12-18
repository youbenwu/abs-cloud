package com.outmao.ebs.bbs.web.api;


import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.dto.plaint.PlaintDTO;
import com.outmao.ebs.bbs.dto.post.*;
import com.outmao.ebs.bbs.dto.subject.*;
import com.outmao.ebs.bbs.entity.Board;
import com.outmao.ebs.bbs.entity.SubjectBrowse;
import com.outmao.ebs.bbs.service.BbsService;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "bbs", tags = "BBS模块接口")
@RestController
@RequestMapping("/api/bbs")
public class BbsAction {

	@Autowired
    private BbsService bbsService;

	//Board
	/*
	 *
	 * 获取版块列表
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取版块列表", notes = "获取版块列表")
	@PostMapping("/board/list")
	public List<Board> getBoardList(){
		return bbsService.getBoardList();
	}




	//Subject
	/*
	 *
	 * 保存主题信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存主题信息", notes = "保存主题信息")
	@PostMapping("/subject/save")
	public void saveSubject(@RequestBody SubjectDTO request){
		bbsService.saveSubject(request);
	}
	/*
	 *
	 * 获取主题信息
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取主题信息", notes = "获取主题信息")
	@PostMapping("/subject/get")
	public SubjectVO getSubjectVOById(Long id){
		return bbsService.getSubjectVOById(id);
	}
	/*
	 *
	 * 获取主题分页列表
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取主题分页列表", notes = "获取主题分页列表")
	@PostMapping("/subject/page")
	public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable){
		return bbsService.getSubjectVOPage(request,pageable);
	}

	/*
	 *
	 * 获取用户收藏的主题分页列表
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户收藏的主题分页列表", notes = "获取用户收藏的主题分页列表")
	@PostMapping("/subject/collection/page")
	public Page<SubjectVO> getSubjectVOPage(GetCollectionSubjectListDTO request, Pageable pageable){
		return bbsService.getSubjectVOPage(request,pageable);
	}

	/*
	 *
	 * 获取用户主题数量
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户发表主题数量", notes = "获取用户发表主题数量")
	@PostMapping("/subject/count")
	public Map<String,Long> getSubjectCount(@RequestBody GetSubjectCountDTO request){
		return bbsService.getSubjectCount(request);
	}


	//SubjectCollection
	/*
	 *
	 * 收藏主题
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "收藏主题", notes = "收藏主题")
	@PostMapping("/subject/collection/save")
	public void saveSubjectCollection(SubjectCollectionDTO request){
		bbsService.saveSubjectCollection(request);
	}
	/*
	 *
	 * 修改收藏主题信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "修改收藏主题信息", notes = "修改收藏主题信息")
	@PostMapping("/subject/collection/modify")
	public void modifySubjectCollection(SubjectCollectionDTO request){
		bbsService.modifySubjectCollection(request);
	}
	/*
	 *
	 * 删除收藏主题
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "删除收藏主题", notes = "删除收藏主题")
	@PostMapping("/subject/collection/delete")
	public void deleteSubjectCollection(Long userId, Long subjectId){
		bbsService.deleteSubjectCollection(userId,subjectId);
	}


	/*
	 *
	 * 获取用户收藏夹数量
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户收藏夹数量", notes = "获取用户收藏夹数量")
	@PostMapping("/subject/collection/count")
	public Map<String,Long> getSubjectCollectionCount(@RequestBody GetSubjectCollectionCountDTO request){
		return bbsService.getSubjectCollectionCount(request);
	}




	//SubjectVote
	/*
	 *
	 * 评价主题
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "评价主题", notes = "评价主题")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "subjectId", value = "主题ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/subject/vote/save")
	public void saveSubjectVote(Long userId, Long subjectId, int vote){
		bbsService.saveSubjectVote(userId,subjectId,vote);
	}
	/*
	 *
	 * 取消评价主题
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "取消评价主题", notes = "取消评价主题")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "subjectId", value = "主题ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/subject/vote/delete")
	public void deleteSubjectVote(Long userId, Long subjectId, int vote){
		bbsService.deleteSubjectVote(userId,subjectId,vote);
	}


	/*
	 *
	 * 主题评分
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "给主题评分", notes = "给主题评分")
	@PostMapping("/subject/grade/save")
	public void saveSubjectGrade(SubjectGradeDTO request){
		bbsService.saveSubjectGrade(request);
	}


	@ApiOperation(value = "删除浏览记录", notes = "删除浏览记录")
	@PostMapping("/subject/browse/delete")
	public void deleteSubjectBrowseById(Long id) {
		bbsService.deleteSubjectBrowseById(id);
	}

	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "删除浏览记录", notes = "删除浏览记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemType", value = "类型 Article--文章 Product--商品", required = true, dataType = "String"),
	})
	@PostMapping("/subject/browse/deleteAll")
	public void deleteSubjectBrowseList(Long userId, String itemType) {
		bbsService.deleteSubjectBrowseList(userId,itemType);
	}

	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取浏览记录列表", notes = "获取浏览记录列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemType", value = "类型 Article--文章 Product--商品", required = true, dataType = "String"),
	})
	@PostMapping("/subject/browse/page")
	public Page<SubjectBrowse> getSubjectBrowsePage(Long userId, String itemType, Pageable pageable) {
		return bbsService.getSubjectBrowsePage(userId,itemType,pageable);
	}


	//Post
	/*
	 *
	 * 保存帖子信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存帖子信息", notes = "保存帖子信息")
	@PostMapping("/post/save")
	public void savePost(@RequestBody PostDTO request){
		bbsService.savePost(request);
	}
	/*
	 *
	 * 设置帖子在主题中是否置顶
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "设置帖子在主题中是否置顶", notes = "设置帖子在主题中是否置顶")
	@PostMapping("/post/setTop")
	public void setPostTop(SetPostTopDTO request){
		bbsService.setPostTop(request);
	}
	/*
	 *
	 * 设置帖子状态
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "设置帖子状态", notes = "设置帖子状态")
	@PostMapping("/post/setStatus")
	public void setPostStatus(SetPostStatusDTO request){
		bbsService.setPostStatus(request);
	}
	/*
	 *
	 * 获取帖子信息
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取帖子信息", notes = "获取帖子信息")
	@PostMapping("/post/get")
	public PostVO getPostVOById(Long id){
		return bbsService.getPostVOById(id);
	}
	/*
	 *
	 * 获取帖子分页列表
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取帖子分页列表", notes = "获取帖子分页列表")
	@PostMapping("/post/page")
	public Page<PostVO> getPostVOPage(@PageableDefault(sort = {"createTime"}, direction = Sort.Direction.ASC)GetPostListDTO request, Pageable pageable){
		return bbsService.getPostVOPage(request,pageable);
	}
	/*
	 *
	 * 获取用户收藏的帖子分页列表
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户收藏的帖子分页列表", notes = "获取用户收藏的帖子分页列表")
	@PostMapping("/post/collection/page")
	public Page<PostVO> getPostVOPage(GetCollectionPostListDTO request, Pageable pageable){
		return bbsService.getPostVOPage(request,pageable);
	}



	//PostCollection
	/*
	 *
	 * 收藏帖子
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "收藏帖子", notes = "收藏帖子")
	@PostMapping("/post/collection/save")
	public void savePostCollection(Long userId, Long postId){
		bbsService.savePostCollection(userId,postId);
	}
	/*
	 *
	 * 取消收藏帖子
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "取消收藏帖子", notes = "取消收藏帖子")
	@PostMapping("/post/collection/delete")
	public void deletePostCollection(Long userId, Long postId){
		bbsService.deletePostCollection(userId,postId);
	}

	//PostVote
	/*
	 *
	 * 评价帖子
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "评价帖子", notes = "评价帖子")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "postId", value = "帖子ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/post/vote/save")
	public void savePostVote(Long userId, Long postId, int vote){
		bbsService.savePostVote(userId,postId,vote);
	}
	/*
	 *
	 * 取消评价帖子
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "取消评价帖子", notes = "取消评价帖子")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "postId", value = "帖子ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/post/vote/delete")
	public void deletePostVote(Long userId, Long postId, int vote){
		bbsService.deletePostVote(userId,postId,vote);
	}


	//Comment
	/*
	 *
	 * 保存评论信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存评论信息", notes = "保存评论信息")
	@PostMapping("/comment/save")
	public void saveComment(CommentDTO request){
		bbsService.saveComment(request);
	}
	/*
	 *
	 * 设置评论状态
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "设置评论状态", notes = "设置评论状态")
	@PostMapping("/comment/setStatus")
	public void setCommentStatus(SetCommentStatusDTO request){
		bbsService.setCommentStatus(request);
	}
	/*
	 *
	 * 获取帖子评论分页列表
	 *
	 * */
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取帖子评论分页列表", notes = "获取帖子评论分页列表")
	@PostMapping("/comment/page")
	public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable){
		return bbsService.getCommentVOPage(request,pageable);
	}


	//CommentVote
	/*
	 *
	 * 评价评论
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "评价评论", notes = "评价评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "commentId", value = "评论ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/comment/vote/save")
	public void saveCommentVote(Long userId, Long commentId, int vote){
		bbsService.saveCommentVote(userId,commentId,vote);
	}
	/*
	 *
	 * 取消评价评论
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "取消评价评论", notes = "取消评价评论")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "commentId", value = "评论ID", required = true, dataType = "Long"),
			@ApiImplicitParam(name = "vote", value = "评价 0--赞成 1--反对", required = true, dataType = "int"),
	})
	@PostMapping("/comment/vote/delete")
	public void deleteCommentVote(Long userId, Long commentId, int vote){
		bbsService.deleteCommentVote(userId,commentId,vote);
	}


	//Plaint
	@PreAuthorize("permitAll")
	@ApiOperation(value = "保存用户投诉", notes = "保存用户投诉")
	@PostMapping("/plaint/save")
	public void savePlaint(@RequestBody PlaintDTO request){
		bbsService.savePlaint(request);
	}


}
