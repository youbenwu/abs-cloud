package com.outmao.ebs.user.web.api;

import com.outmao.ebs.user.dto.FeedbackDTO;
import com.outmao.ebs.user.entity.Feedback;
import com.outmao.ebs.user.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "user-feedback", tags = "用户-反馈")
@RestController
@RequestMapping("/api/user/feedback")
public class FeedbackAction {

	@Autowired
	private FeedbackService feedbackService;

	@ApiOperation(value = "保存用户反馈", notes = "保存用户反馈")
	@PostMapping("/save")
	public Feedback saveFeedback(FeedbackDTO request){
		return feedbackService.saveFeedback(request);
	}



}
