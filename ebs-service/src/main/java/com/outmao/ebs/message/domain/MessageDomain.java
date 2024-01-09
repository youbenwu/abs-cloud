package com.outmao.ebs.message.domain;


import com.outmao.ebs.message.dto.*;
import com.outmao.ebs.message.entity.Message;
import com.outmao.ebs.message.vo.MessageVO;
import com.outmao.ebs.message.vo.UserMessageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MessageDomain {

	public Message saveMessage(MessageDTO request);
	public void deleteMessageById(Long id);
	public void setMessageStatus(SetMessageStatusDTO request);
	public Page<MessageVO> getMessageVOPage(GetMessageListDTO request, Pageable pageable);


    public void setUserMessageStatus(SetUserMessageStatusDTO request);
	public Page<UserMessageVO> getUserMessageVOPage(GetUserMessageListDTO request, Pageable pageable);
	public List<Map<String, Integer>> getUnreadCount(Long userId);
	public List<Map<String, UserMessageVO>> getLastUserMessageVO(Long userId);

	public void sendMessageById(Long id);


}
