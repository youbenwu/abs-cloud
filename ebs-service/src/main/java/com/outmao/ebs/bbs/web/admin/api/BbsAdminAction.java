package com.outmao.ebs.bbs.web.admin.api;

import com.outmao.ebs.bbs.dto.board.BoardDTO;
import com.outmao.ebs.bbs.dto.comment.CommentDTO;
import com.outmao.ebs.bbs.dto.comment.GetCommentListDTO;
import com.outmao.ebs.bbs.dto.comment.SetCommentStatusDTO;
import com.outmao.ebs.bbs.dto.plaint.GetPlaintListDTO;
import com.outmao.ebs.bbs.dto.post.GetPostListDTO;
import com.outmao.ebs.bbs.dto.post.PostDTO;
import com.outmao.ebs.bbs.dto.post.SetPostStatusDTO;
import com.outmao.ebs.bbs.dto.post.SetPostTopDTO;
import com.outmao.ebs.bbs.dto.subject.GetSubjectListDTO;
import com.outmao.ebs.bbs.dto.subject.SubjectDTO;
import com.outmao.ebs.bbs.entity.Board;
import com.outmao.ebs.bbs.entity.Plaint;
import com.outmao.ebs.bbs.service.BbsService;
import com.outmao.ebs.bbs.vo.CommentVO;
import com.outmao.ebs.bbs.vo.PostVO;
import com.outmao.ebs.bbs.vo.SubjectVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AccessPermissionGroup(title="BBS管理",url="/bbs",name="",children = {
		@AccessPermissionParent(title = "板块管理",url = "/bbs/board",name = "",children = {
				@AccessPermission(title = "保存板块",url = "/bbs/board",name = "save"),
				@AccessPermission(title = "删除板块",url = "/bbs/board",name = "delete"),
				@AccessPermission(title = "读取板块",url = "/bbs/board",name = "read"),
		}),
		@AccessPermissionParent(title = "主题管理",url = "/bbs/subject",name = "",children = {
				@AccessPermission(title = "保存主题",url = "/bbs/subject",name = "save"),
				@AccessPermission(title = "删除主题",url = "/bbs/subject",name = "delete"),
				@AccessPermission(title = "读取主题",url = "/bbs/subject",name = "read"),
				@AccessPermission(title = "审核主题",url = "/bbs/subject",name = "status"),
		}),
		@AccessPermissionParent(title = "帖子管理",url = "/bbs/post",name = "",children = {
				@AccessPermission(title = "保存帖子",url = "/bbs/post",name = "save"),
				@AccessPermission(title = "删除帖子",url = "/bbs/post",name = "delete"),
				@AccessPermission(title = "读取帖子",url = "/bbs/post",name = "read"),
				@AccessPermission(title = "审核帖子",url = "/bbs/post",name = "status"),
		}),
		@AccessPermissionParent(title = "评论管理",url = "/bbs/comment",name = "",children = {
				@AccessPermission(title = "保存评论",url = "/bbs/comment",name = "save"),
				@AccessPermission(title = "删除评论",url = "/bbs/comment",name = "delete"),
				@AccessPermission(title = "读取评论",url = "/bbs/comment",name = "read"),
				@AccessPermission(title = "审核评论",url = "/bbs/comment",name = "status"),
		}),
		@AccessPermissionParent(title = "投诉管理",url = "/bbs/plaint",name = "",children = {
				@AccessPermission(title = "保存投诉",url = "/bbs/plaint",name = "save"),
				@AccessPermission(title = "删除投诉",url = "/bbs/plaint",name = "delete"),
				@AccessPermission(title = "读取投诉",url = "/bbs/plaint",name = "read"),
				@AccessPermission(title = "审核投诉",url = "/bbs/plaint",name = "status"),
		}),
})

@Api(value = "admin-bbs", tags = "后台-BBS模块接口")
@RestController
@RequestMapping("/api/admin/bbs")
public class BbsAdminAction {

	@Autowired
    private BbsService bbsService;

	//Board
	/*
	 *
	 * 保存版块信息
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/board','save')")
	@ApiOperation(value = "保存板块", notes = "保存板块")
	@PostMapping("/board/save")
	public void saveBoard(BoardDTO request){
		bbsService.saveBoard(request);
	}
	/*
	 *
	 * 删除版块信息
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/board','delete')")
	@ApiOperation(value = "删除版块", notes = "删除版块")
	@PostMapping("/board/delete")
	public void deleteBoardById(Long id){
		bbsService.deleteBoardById(id);
	}
	/*
	 *
	 * 获取版块列表
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/board','delete')")
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
	@PreAuthorize("hasPermission('/bbs/subject','save')")
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
	@PreAuthorize("hasPermission('/bbs/subject','read')")
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
	@PreAuthorize("hasPermission('/bbs/subject','read')")
	@ApiOperation(value = "获取主题信息", notes = "获取主题信息")
	@PostMapping("/subject/page")
	public Page<SubjectVO> getSubjectVOPage(GetSubjectListDTO request, Pageable pageable){
		return getSubjectVOPage(request,pageable);
	}


	//Post
	/*
	 *
	 * 保存帖子信息
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/post','save')")
	@ApiOperation(value = "保存帖子信息", notes = "保存帖子信息")
	@PostMapping("/post/save")
	public void savePost(PostDTO request){
		bbsService.savePost(request);
	}
	@PreAuthorize("hasPermission('/bbs/post','save')")
	@ApiOperation(value = "设置帖子置顶", notes = "设置帖子置顶")
	@PostMapping("/post/setTop")
	public void setPostTop(SetPostTopDTO request){
		bbsService.setPostTop(request);
	}

	/*
	 *
	 * 设置帖子状态
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/post','status')")
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
	@PreAuthorize("hasPermission('/bbs/post','read')")
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
	@PreAuthorize("hasPermission('/bbs/post','read')")
	@ApiOperation(value = "获取帖子分页列表", notes = "获取帖子分页列表")
	@PostMapping("/post/page")
	public Page<PostVO> getPostVOPage(GetPostListDTO request, Pageable pageable){
		return bbsService.getPostVOPage(request,pageable);
	}


	//Comment
	/*
	 *
	 * 保存评论信息
	 *
	 * */
	@PreAuthorize("hasPermission('/bbs/comment','save')")
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
	@PreAuthorize("hasPermission('/bbs/comment','status')")
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
	@PreAuthorize("hasPermission('/bbs/comment','read')")
	@ApiOperation(value = "获取帖子评论分页列表", notes = "获取帖子评论分页列表")
	@PostMapping("/comment/page")
	public Page<CommentVO> getCommentVOPage(GetCommentListDTO request, Pageable pageable){
		return bbsService.getCommentVOPage(request,pageable);
	}



	//Plaint

	@PreAuthorize("hasPermission('/bbs/plaint','status')")
	@ApiOperation(value = "设置投诉状态", notes = "设置投诉状态")
	@PostMapping("/plaint/setStatus")
	public Plaint setPlaintStatus(Long id, int status, String statusRemark){
		return bbsService.setPlaintStatus(id,status,statusRemark);
	}

	@PreAuthorize("hasPermission('/bbs/plaint','read')")
	@ApiOperation(value = "获取投诉列表", notes = "获取投诉列表")
	@PostMapping("/plaint/page")
	public Page<Plaint> getPlaintPage(@RequestBody GetPlaintListDTO request, Pageable pageable){
		return bbsService.getPlaintPage(request,pageable);
	}



}
