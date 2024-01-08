package com.outmao.ebs.sys.web.admin.api;

import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.sys.dto.FeedbackItemDTO;
import com.outmao.ebs.sys.dto.GetFeedbackItemListDTO;
import com.outmao.ebs.sys.dto.GetFeedbackListDTO;
import com.outmao.ebs.sys.dto.SetFeedbackStatusDTO;
import com.outmao.ebs.sys.entity.Feedback;
import com.outmao.ebs.sys.entity.FeedbackItem;
import com.outmao.ebs.sys.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AccessPermissionGroup(title="系统管理",url="/sys",name="",children = {

		@AccessPermissionParent(title = "用户反馈管理",url = "/sys/feedback",name = "",children = {
				@AccessPermission(title = "保存用户反馈",url = "/sys/feedback",name = "save"),
				@AccessPermission(title = "删除用户反馈",url = "/sys/feedback",name = "delete"),
				@AccessPermission(title = "读取用户反馈",url = "/sys/feedback",name = "read"),
				@AccessPermission(title = "审核用户反馈",url = "/sys/feedback",name = "status"),
		}),

})

@Api(value = "admin-user-feedback", tags = "后台-系统-反馈")
@RestController
@RequestMapping("/api/admin/sys/feedback")
public class FeedbackAdminAction {

	@Autowired
	private FeedbackService feedbackService;


	@PreAuthorize("hasPermission('/sys/feedback/item','save')")
	@ApiOperation(value = "新增反馈项", notes = "新增反馈项")
	@PostMapping("/item/save")
	public FeedbackItem saveFeedbackItem(FeedbackItemDTO request){
		return feedbackService.saveFeedbackItem(request);
	}

	@PreAuthorize("hasPermission('/sys/feedback/item','delete')")
	@ApiOperation(value = "删除反馈项", notes = "删除反馈项")
	@PostMapping("/item/delete")
	public void deleteFeedbackItemById(Long id){
		feedbackService.deleteFeedbackItemById(id);
	}

	@PreAuthorize("hasPermission('/sys/feedback/item','read')")
	@ApiOperation(value = "获取反馈项列表", notes = "获取反馈项列表")
	@PostMapping("/item/list")
	public List<FeedbackItem> getFeedbackItemList(GetFeedbackItemListDTO request){
		return feedbackService.getFeedbackItemList(request);
	}

	@PreAuthorize("hasPermission('/sys/feedback/item','read')")
	@ApiOperation(value = "获取反馈项列表", notes = "获取反馈项列表")
	@PostMapping("/item/page")
	public Page<FeedbackItem> getFeedbackItemPage(GetFeedbackItemListDTO request,Pageable pageable){
		return feedbackService.getFeedbackItemPage(request,pageable);
	}

	@PreAuthorize("hasPermission('/sys/feedback','delete')")
	@ApiOperation(value = "删除用户反馈", notes = "删除获取用户反馈")
	@PostMapping("/delete")
	public void deleteFeedbackById(Long id){
		feedbackService.deleteFeedbackById(id);
	}

	@PreAuthorize("hasPermission('/sys/feedback','status')")
	@ApiOperation(value = "设置用户反馈状态", notes = "设置用户反馈状态")
	@PostMapping("/setStatus")
	public void setFeedbackStatus(SetFeedbackStatusDTO request){
		feedbackService.setFeedbackStatus(request);
	}

	@PreAuthorize("hasPermission('/sys/feedback','read')")
	@ApiOperation(value = "获取用户反馈列表", notes = "获取用户反馈列表")
	@PostMapping("/page")
	public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable) {
		return feedbackService.getFeedbackPage(request,pageable);
	}



}
