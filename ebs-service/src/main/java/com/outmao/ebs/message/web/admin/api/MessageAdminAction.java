package com.outmao.ebs.message.web.admin.api;


import com.outmao.ebs.message.dto.*;
import com.outmao.ebs.message.entity.MessageTemplate;
import com.outmao.ebs.message.service.MessageService;
import com.outmao.ebs.message.vo.MessageTypeVO;
import com.outmao.ebs.message.vo.MessageVO;
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

@Api(value = "account-message", tags = "后台--消息")
@RestController
@RequestMapping("/api/admin/message")
public class MessageAdminAction {

	@Autowired
    MessageService messageService;


	@ApiOperation(value = "保存消息模板", notes = "保存消息模板")
	@PostMapping("/template/save")
	public MessageTemplate saveMessageTemplate(MessageTemplateDTO params) {
		return messageService.saveMessageTemplate(params);
	}

	@ApiOperation(value = "删除消息模板", notes = "删除消息模板")
	@PostMapping("/template/delete")
	public void deleteMessageTemplateById(Long id) {
		messageService.deleteMessageTemplateById(id);
	}

	@ApiOperation(value = "获取消息模板", notes = "获取消息模板")
	@PostMapping("/template/get")
	public MessageTemplate getMessageTemplateById(Long id) {
		return messageService.getMessageTemplateById(id);
	}


	@ApiOperation(value = "获取消息模板", notes = "获取消息模板")
	@PostMapping("/template/list")
	public List<MessageTemplate> getMessageTemplateList(Long typeId) {
		return messageService.getMessageTemplateList(typeId);
	}

	@ApiOperation(value = "保存消息类型", notes = "保存消息类型")
	 @PostMapping("/type/save")
	public void saveMessageType(@RequestBody MessageTypeDTO request) {
		messageService.saveMessageType(request);
	}

	@ApiOperation(value = "删除消息类型", notes = "删除消息类型")
	@PostMapping("/type/delete")
	public void deleteMessageTypeById(Long id) {
		messageService.deleteMessageTypeById(id);
	}

	@ApiOperation(value = "获取消息类型", notes = "获取消息类型")
	@PostMapping("/type/get")
	public MessageTypeVO getMessageTypeVOById(Long id) {
		return messageService.getMessageTypeVOById(id);
	}

	@ApiOperation(value = "获取消息类型", notes = "获取消息类型")
	@PostMapping("/type/getByName")
	public MessageTypeVO getMessageTypeVOByName(String name) {
		return messageService.getMessageTypeVOByName(name);
	}


	@ApiOperation(value = "获取消息类型", notes = "获取消息类型")
	@PostMapping("/type/list")
	public List<MessageTypeVO> getMessageTypeVOList() {
		return messageService.getMessageTypeVOList();
	}


	@ApiOperation(value = "发送站内消息", notes = "发送站内消息")
	@PostMapping("/send")
	public void sendMessageAsync(@RequestBody MessageDTO params) {
		messageService.sendMessageAsync(params);
	}

	//发送模板消息
	@ApiOperation(value = "发送模板消息", notes = "发送模板消息")
	@PostMapping("/sendByType")
	public void sendMessageAsync(@RequestBody SendMessageByTypeDTO params){
		messageService.sendMessageAsync(params);
	}

	@ApiOperation(value = "删除消息", notes = "删除消息")
	@PostMapping("/delete")
	public void deleteMessageById(Long id) {
		messageService.deleteMessageById(id);
	}

	@ApiOperation(value = "获取站内消息列表", notes = "获取站内消息列表")
	@ApiImplicitParams({ 
		    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "页码 (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "每页数据个数"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "排序相关的信息") })
	@PostMapping("/page")
	public Page<MessageVO> getMessageVOPage(@RequestBody GetMessageListDTO request, @PageableDefault(page = 0, size = 10, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<MessageVO> page = messageService.getMessageVOPage(request, pageable);
		return page;
	}





}
