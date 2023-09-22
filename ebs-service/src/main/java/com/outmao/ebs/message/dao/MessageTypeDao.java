package com.outmao.ebs.message.dao;


import com.outmao.ebs.message.entity.MessageTemplate;
import com.outmao.ebs.message.entity.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MessageTypeDao extends JpaRepository<MessageType, Long> {
	
	public MessageType findByName(String name);

	@Modifying
	@Query("update MessageType m set m.msgTemplate=null,m.emailTemplate=null,m.smsTemplate=null,m.pushTemplate=null where m.id=?1")
	public void setTemplateNullById(Long id);


	@Modifying
	@Query("update MessageType m set m.msgTemplate=?2 where m.id=?1")
	public void setMsgTemplateById(Long id, MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.emailTemplate=?2 where m.id=?1")
	public void setEmailTemplateById(Long id, MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.smsTemplate=?2 where m.id=?1")
	public void setSmsTemplateById(Long id, MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.pushTemplate=?2 where m.id=?1")
	public void setPushTemplateById(Long id, MessageTemplate template);


	@Modifying
	@Query("update MessageType m set m.msgTemplate=null where m.msgTemplate=?1")
	public void setMsgTemplateNullByTemplate(MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.emailTemplate=null where m.emailTemplate=?1")
	public void setEmailTemplateNullByTemplate(MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.smsTemplate=null where m.smsTemplate=?1")
	public void setSmsTemplateNullByTemplate(MessageTemplate template);

	@Modifying
	@Query("update MessageType m set m.pushTemplate=null where m.pushTemplate=?1")
	public void setPushTemplateNullByTemplate(MessageTemplate template);


	@Modifying
	@Query("update MessageType m set m.mpTemplate=null where m.mpTemplate=?1")
	public void setMpTemplateNullByTemplate(MessageTemplate template);

}
