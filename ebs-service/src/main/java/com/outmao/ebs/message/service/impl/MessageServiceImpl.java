package com.outmao.ebs.message.service.impl;


import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.FreemarkerTemplateUtil;
import com.outmao.ebs.message.domain.MessageDomain;
import com.outmao.ebs.message.domain.MessageTypeDomain;
import com.outmao.ebs.message.dto.*;
import com.outmao.ebs.message.entity.Message;
import com.outmao.ebs.message.entity.MessageTemplate;
import com.outmao.ebs.message.entity.MessageType;
import com.outmao.ebs.message.service.MessageService;
import com.outmao.ebs.message.vo.MessageTemplateVO;
import com.outmao.ebs.message.vo.MessageTypeVO;
import com.outmao.ebs.message.vo.MessageVO;
import com.outmao.ebs.message.vo.UserMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageTypeDomain messageTypeDomain;


	@Autowired
    private MessageDomain messageDomain;



	@Async
	@Override
	public void sendMessageAsync(MessageDTO request) {
		Message m = messageDomain.saveMessage(request);
		messageDomain.sendMessageById(m.getId());
	}


	@Async
	@Override
	public void sendMessageAsync(SendMessageByTypeDTO request) {


		MessageTypeVO category= messageTypeDomain.getMessageTypeVOByName(request.getType());

		if(category==null){
			System.out.println("----消息类型【"+request.getType()+"】不存在----");
			return;
		}

		List<MessageTemplateVO> list=new ArrayList<>();

		if(category.isMsg()&&category.getMsgTemplate()!=null){
			list.add(category.getMsgTemplate());
		}
		if(category.isEmail()&&category.getEmailTemplate()!=null){
			list.add(category.getEmailTemplate());
		}
		if(category.isPush()&&category.getPushTemplate()!=null){
			list.add(category.getPushTemplate());
		}
		if(category.isSms()&&category.getSmsTemplate()!=null){
			list.add(category.getSmsTemplate());
		}

		if(category.isMp()&&category.getMpTemplate()!=null){
			list.add(category.getMpTemplate());
		}

		if(list.isEmpty()){
			System.out.println("----消息模板不存在----");
			return;
		}

		for (MessageTemplateVO t:list){
			try {

				String title = t.getTitle()==null?null: FreemarkerTemplateUtil.process(t.getTitle(), request.getData());
				String content= t.getContent()==null?null: FreemarkerTemplateUtil.process(t.getContent(), request.getData());
				String url=t.getUrl()==null?null: FreemarkerTemplateUtil.process(t.getUrl(), request.getData());

				MessageDTO p=new MessageDTO();
				p.setItem(request.getItem());
				p.setFromId(request.getFromId());
				p.setTos(request.getTos());
				p.setType(request.getType());
				p.setSendType(t.getSendType());
				p.setTemplateId(t.getTemplateId());
				p.setTitle(title);
				p.setContent(content);
				p.setUrl(url);

				this.sendMessageAsync(p);

			}catch (Exception e){
				e.printStackTrace();
				throw new BusinessException(e.getMessage());
			}

		}

	}


	@Override
	public void deleteMessageById(Long id) {
		messageDomain.deleteMessageById(id);
	}


	@Override
	public MessageType saveMessageType(MessageTypeDTO request) {
		return messageTypeDomain.saveMessageType(request);
	}

	@Override
	public void deleteMessageTypeById(Long id) {
        messageTypeDomain.deleteMessageTypeById(id);
	}

	@Override
	public MessageType getMessageTypeById(Long id) {
		return messageTypeDomain.getMessageTypeById(id);
	}

	@Override
	public MessageType getMessageTypeByName(String name) {
		return messageTypeDomain.getMessageTypeByName(name);
	}

	@Override
	public List<MessageType> getMessageTypeList() {
		return messageTypeDomain.getMessageTypeList();
	}

	@Override
	public MessageTypeVO getMessageTypeVOByName(String name) {
		return messageTypeDomain.getMessageTypeVOByName(name);
	}

	@Override
	public MessageTypeVO getMessageTypeVOById(Long id) {
		return messageTypeDomain.getMessageTypeVOById(id);
	}

	@Override
	public List<MessageTypeVO> getMessageTypeVOList() {
		return messageTypeDomain.getMessageTypeVOList();
	}

	@Override
	public MessageTemplate saveMessageTemplate(MessageTemplateDTO request) {
		return messageTypeDomain.saveMessageTemplate(request);
	}

	@Override
	public void deleteMessageTemplateById(Long id) {
        messageTypeDomain.deleteMessageTemplateById(id);
	}

	@Override
	public MessageTemplate getMessageTemplateById(Long id) {
		return messageTypeDomain.getMessageTemplateById(id);
	}

	@Override
	public List<MessageTemplate> getMessageTemplateList(Long typeId) {
		return messageTypeDomain.getMessageTemplateList(typeId);
	}

	@Override
	public List<MessageTemplate> getMessageTemplateList(Long typeId, int sendType) {
		return messageTypeDomain.getMessageTemplateList(typeId,sendType);
	}

	@Override
	public Page<MessageTypeVO> getMessageTypeVOPage(Pageable pageable) {
		return messageTypeDomain.getMessageTypeVOPage(pageable);
	}

	@Override
	public Page<MessageTemplateVO> getMessageTemplateVOPage(GetMessageTemplateListDTO request, Pageable pageable) {
		return messageTypeDomain.getMessageTemplateVOPage(request,pageable);
	}

	@Override
	public MessageTemplateVO getMessageTemplateVOById(Long id) {
		return messageTypeDomain.getMessageTemplateVOById(id);
	}

	@Override
	public Page<MessageVO> getMessageVOPage(GetMessageListDTO request, Pageable pageable) {
		return messageDomain.getMessageVOPage(request,pageable);
	}

	@Override
	public Page<UserMessageVO> getUserMessageVOPage(GetUserMessageListDTO request, Pageable pageable) {
		return messageDomain.getUserMessageVOPage(request,pageable);
	}

	@Override
	public List<Map<String, Integer>> getUnreadCount(Long userId) {
		return messageDomain.getUnreadCount(userId);
	}

	@Override
	public List<Map<String, UserMessageVO>> getLastUserMessageVO(Long userId) {
		return messageDomain.getLastUserMessageVO(userId);
	}

	@Override
	public Message saveMessage(MessageDTO request) {
		return messageDomain.saveMessage(request);
	}

	@Override
	public void setMessageStatus(SetMessageStatusDTO request) {
		messageDomain.setMessageStatus(request);
	}

	@Override
	public void setUserMessageStatus(SetUserMessageStatusDTO request) {
		messageDomain.setUserMessageStatus(request);
	}

	@Override
	public void sendMessageById(Long id) {
		messageDomain.sendMessageById(id);
	}

}
