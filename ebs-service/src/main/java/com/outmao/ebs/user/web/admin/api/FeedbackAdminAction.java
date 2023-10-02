package com.outmao.ebs.user.web.admin.api;

import com.outmao.ebs.user.dto.GetFeedbackListDTO;
import com.outmao.ebs.user.dto.SetFeedbackStatusDTO;
import com.outmao.ebs.user.entity.Feedback;
import com.outmao.ebs.user.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "account-user-feedback", tags = "后台-用户-反馈")
@RestController
@RequestMapping("/api/admin/user/feedback")
public class FeedbackAdminAction {

	@Autowired
	private FeedbackService feedbackService;

	@PreAuthorize("hasPermission('/user/feedback','delete')")
	@ApiOperation(value = "删除用户反馈", notes = "删除获取用户反馈")
	@PostMapping("/delete")
	public void deleteFeedbackById(Long id){
		feedbackService.deleteFeedbackById(id);
	}

	@PreAuthorize("hasPermission('/user/feedback','status')")
	@ApiOperation(value = "设置用户反馈状态", notes = "设置用户反馈状态")
	@PostMapping("/setStatus")
	public void setFeedbackStatus(SetFeedbackStatusDTO request){
		feedbackService.setFeedbackStatus(request);
	}

	@PreAuthorize("hasPermission('/user/feedback','read')")
	@ApiOperation(value = "获取用户反馈列表", notes = "获取用户反馈列表")
	@PostMapping("/page")
	public Page<Feedback> getFeedbackPage(GetFeedbackListDTO request, Pageable pageable) {
		return feedbackService.getFeedbackPage(request,pageable);
	}


}
