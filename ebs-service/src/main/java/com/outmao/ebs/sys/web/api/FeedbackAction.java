package com.outmao.ebs.sys.web.api;

import com.outmao.ebs.sys.dto.FeedbackDTO;
import com.outmao.ebs.sys.dto.GetFeedbackItemListDTO;
import com.outmao.ebs.sys.entity.Feedback;
import com.outmao.ebs.sys.entity.FeedbackItem;
import com.outmao.ebs.sys.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "sys-feedback", tags = "系统-反馈")
@RestController
@RequestMapping("/api/sys/feedback")
public class FeedbackAction {

	@Autowired
	private FeedbackService feedbackService;

	@ApiOperation(value = "获取反馈项列表", notes = "获取反馈项列表")
	@PostMapping("/item/list")
	public List<FeedbackItem> getFeedbackItemList(GetFeedbackItemListDTO request){
		return feedbackService.getFeedbackItemList(request);
	}

	@ApiOperation(value = "保存用户反馈", notes = "保存用户反馈")
	@PostMapping("/save")
	public Feedback saveFeedback(FeedbackDTO request){
		return feedbackService.saveFeedback(request);
	}



}
