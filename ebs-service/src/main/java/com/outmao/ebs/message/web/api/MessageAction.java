package com.outmao.ebs.message.web.api;


import com.outmao.ebs.message.dto.GetUserMessageListDTO;
import com.outmao.ebs.message.dto.SetUserMessageStatusDTO;
import com.outmao.ebs.message.service.MessageService;
import com.outmao.ebs.message.vo.UserMessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "message", tags = "消息")
@RestController
@RequestMapping("/api/message")
public class MessageAction {

	@Autowired
    MessageService messageService;

	@ApiOperation(value = "获取用户消息列表", notes = "获取用户消息列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "排序相关的信息") })
	@PostMapping("/page")
	public Page<UserMessageVO> getUserMessageVOPage(@RequestBody GetUserMessageListDTO request, @PageableDefault(page = 0, size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return messageService.getUserMessageVOPage(request, pageable);
	}

	@ApiOperation(value = "设置消息已读状态", notes = "设置消息已读状态")
	@PostMapping("/setStatus")
	public void setUserMessageStatus(@RequestBody SetUserMessageStatusDTO request){
		messageService.setUserMessageStatus(request);
	}

	@ApiOperation(value = "获取用户最后一条消息,按消息类型分组", notes = "获取用户最后一条消息,按消息类型分组")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"), })
	@PostMapping("/lastGroupByType")
	public List<Map<String, UserMessageVO>> getLastUserMessageVO(Long userId){
		return messageService.getLastUserMessageVO(userId);
	}

	@ApiOperation(value = "获取用户未读消息数量,按消息类型分组", notes = "获取用户未读消息数量,按消息类型分组")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"), })
	@PostMapping("/unreadCountGroupByType")
	public Object getUnreadCount(Long userId) {
		return messageService.getUnreadCount(userId);
	}


}
