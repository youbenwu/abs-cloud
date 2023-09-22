package com.outmao.ebs.message.service;


import com.outmao.ebs.message.dto.*;
import com.outmao.ebs.message.entity.Message;
import com.outmao.ebs.message.entity.MessageTemplate;
import com.outmao.ebs.message.entity.MessageType;
import com.outmao.ebs.message.vo.MessageTypeVO;
import com.outmao.ebs.message.vo.MessageVO;
import com.outmao.ebs.message.vo.UserMessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MessageService {


	public MessageType saveMessageType(MessageTypeDTO request);
	public void deleteMessageTypeById(Long id);
	public MessageType getMessageTypeById(Long id);
	public MessageType getMessageTypeByName(String name);
	public List<MessageType> getMessageTypeList();


	public MessageTypeVO getMessageTypeVOByName(String name);
	public MessageTypeVO getMessageTypeVOById(Long id);
	public List<MessageTypeVO> getMessageTypeVOList();


	public MessageTemplate saveMessageTemplate(MessageTemplateDTO request);
	public void deleteMessageTemplateById(Long id);
	public MessageTemplate getMessageTemplateById(Long id);
	public List<MessageTemplate> getMessageTemplateList(Long typeId);
	public List<MessageTemplate> getMessageTemplateList(Long typeId, int sendType);


	public Message saveMessage(MessageDTO request);
	public void deleteMessageById(Long id);
	public void setMessageStatus(SetMessageStatusDTO request);
	public Page<MessageVO> getMessageVOPage(GetMessageListDTO request, Pageable pageable);

	public void setUserMessageStatus(SetUserMessageStatusDTO request);
	public Page<UserMessageVO> getUserMessageVOPage(GetUserMessageListDTO request, Pageable pageable);
	public List<Map<String, Integer>> getUnreadCount(Long userId);
	public List<Map<String, UserMessageVO>> getLastUserMessageVO(Long userId);

	public void sendMessageById(Long id);


    //发送消息
	public void sendMessageAsync(MessageDTO request);
	//发送模板消息
	public void sendMessageAsync(SendMessageByTypeDTO request);






}
