package com.outmao.ebs.message.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.services.jiguang.PushService;
import com.outmao.ebs.common.services.mail.MailService;
import com.outmao.ebs.common.services.sms.SmsService;
import com.outmao.ebs.common.services.wxmp.WXMP;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.message.common.constant.MessageStatus;
import com.outmao.ebs.message.common.constant.SendType;
import com.outmao.ebs.message.dao.MessageDao;
import com.outmao.ebs.message.dao.UserMessageDao;
import com.outmao.ebs.message.domain.MessageDomain;
import com.outmao.ebs.message.domain.conver.MessageVOConver;
import com.outmao.ebs.message.domain.conver.UserMessageVOConver;
import com.outmao.ebs.message.dto.*;
import com.outmao.ebs.message.entity.Message;
import com.outmao.ebs.message.entity.QMessage;
import com.outmao.ebs.message.entity.QUserMessage;
import com.outmao.ebs.message.entity.UserMessage;
import com.outmao.ebs.message.vo.MessageVO;
import com.outmao.ebs.message.vo.UserMessageVO;
import com.outmao.ebs.org.dao.MemberDao;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dao.UserDetailsDao;
import com.outmao.ebs.user.dao.UserOauthDao;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.OnlineService;
import com.outmao.ebs.user.vo.UserEmailVO;
import com.outmao.ebs.user.vo.UserOpenIdVO;
import com.outmao.ebs.user.vo.UserPhoneVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class MessageDomainImpl extends BaseDomain implements MessageDomain {



	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserMessageDao userMessageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private UserOauthDao userOauthDao;
	
	@Autowired
	private PushService pushService;

	@Autowired
	private MailService mailSender;

	@Autowired
	private SmsService smsService;

	@Autowired
	private WXMP wxmp;

	@Autowired
	private OnlineService onlineService;


	private MessageVOConver messageVOConver=new MessageVOConver();
    private UserMessageVOConver userMessageVOConver=new UserMessageVOConver();


	@Transactional
	@Override
	public Message saveMessage(MessageDTO request) {
		Message m = new Message();
		BeanUtils.copyProperties(request, m,"tos");
		m.setFrom(request.getFromId()!=null?userDao.getOne(request.getFromId()):userDao.findByUsername("account"));
		if(request.getTos()!=null){
			String tos= JsonUtil.toJson(request.getTos());
			m.setTos(tos);
		}else{
			m.setTos("[]");
		}
		m.setCreateTime(new Date());
		m.setUpdateTime(new Date());
		m = messageDao.save(m);
		return m;
	}

	@Transactional
	@Override
	public void deleteMessageById(Long id) {
		userMessageDao.deleteAllByMessageId(id);
		messageDao.deleteById(id);
	}

	@Transactional
	@Override
	public void setMessageStatus(SetMessageStatusDTO request) {
		Message m = messageDao.getOne(request.getId());
		m.setStatus(request.getStatus());
		m.setStatusRemark(request.getStatusRemark());
		messageDao.save(m);
	}


	@SetSimpleUser
	@Override
	public Page<MessageVO> getMessageVOPage(GetMessageListDTO request, Pageable pageable) {
		QMessage e = QMessage.message;
		Predicate p=null ;

		if(request.getSendType()!=null){
			p=e.sendType.eq(request.getSendType());
		}

		if(request.getTypes()!=null&&request.getTypes().size()>0){
			p=e.type.in(request.getTypes()).and(p);
		}

		Page<MessageVO> page=queryPage(e,p,messageVOConver,pageable);

		return page;
	}


	@Transactional
	public UserMessage saveUserMessage(Message message, User from, User to, String target) {
		UserMessage um = new UserMessage();
		um.setMessage(message);
		um.setFrom(from);
		um.setTo(to);
		um.setType(message.getType());
		um.setTarget(target);
		um.setSendType(message.getSendType());
		um.setCreateTime(new Date());
		um=userMessageDao.save(um);

		onlineService.saveOnline(to.getId(),true);

		return um;
	}

	@Transactional
	@Override
	public void setUserMessageStatus(SetUserMessageStatusDTO request) {
		QUserMessage e = QUserMessage.userMessage;
		QF.update(e).set(e.status, request.getStatus()).where(e.id.in(request.getIds())).execute();
	}

	@SetSimpleUser
	@Override
	public Page<UserMessageVO> getUserMessageVOPage(GetUserMessageListDTO request, Pageable pageable) {

		QUserMessage e = QUserMessage.userMessage;


		Predicate p=e.to.id.eq(request.getUserId());

		if(request.getTypes()!=null&&request.getTypes().size()>0){
			p=e.type.in(request.getTypes()).and(p);
		}

		p=e.sendType.eq(SendType.MSG.getType()).and(p);

		Page<UserMessageVO> page=queryPage(e,p,userMessageVOConver,pageable);


		return page;
	}

	@Override
	public List<Map<String, Integer>> getUnreadCount(Long userId) {
		QUserMessage e = QUserMessage.userMessage;
		List<Tuple> list = QF.select(e.type, e.id.count()).from(e)
				.where(e.to.id.eq(userId).and(e.status.eq(0)).and(e.sendType.eq(SendType.MSG.getType()))).groupBy(e.type).fetch();
		List<Map<String, Integer>> vs = new ArrayList<>();
		for (Tuple t : list) {
			Map<String, Integer> v = new HashMap<>();
			v.put(t.get(e.type), t.get(e.id.count()).intValue());
			vs.add(v);
		}
		return vs;
	}

	@Override
	public List<Map<String, UserMessageVO>> getLastUserMessageVO(Long userId) {
		QUserMessage e = QUserMessage.userMessage;
		List<String> list = QF.select(e.type).from(e)
				.where(
						e.to.id.eq(userId)
						.and(e.sendType.eq(SendType.MSG.getType()))
				).groupBy(e.type).fetch();

		List<Map<String, UserMessageVO>> vs = new ArrayList<>();
		for (String category : list) {
			Map<String, UserMessageVO> v = new HashMap<>();

			UserMessageVO vo=queryOne(e,QF.select(userMessageVOConver.select(e)).from(e)
					.where(
							e.to.id.eq(userId)
							.and(e.type.eq(category))
							.and(e.sendType.eq(SendType.MSG.getType()))
					).orderBy(e.id.desc())
					.offset(0).limit(1),userMessageVOConver);

			v.put(category, vo);
			vs.add(v);
		}

		return vs;
	}


	@Transactional
	@Override
	public void sendMessageById(Long id) {

		Message message = messageDao.getOne(id);


		Collection<Long> tos=JsonUtil.fromJson(message.getTos(),List.class,Long.class);

		if(tos.isEmpty()){
			if(message.getOrgId()!=null){
				tos=memberDao.findUserIdAllByOrgId(message.getOrgId());
			}else {
				//发送前10000000个用户
				tos = userDao.findIdAll(PageRequest.of(0, 10000000, Sort.by(Direction.DESC, "loginTime")));
			}
		}

		Map<Long,String> targets=null;

		if(message.getSendType()==SendType.SMS.getType()){
			Collection<UserPhoneVO> list=userDetailsDao.findAllUserPhoneByUserIdIn(tos);
			targets=list.stream().collect(Collectors.toMap(t->t.getUserId(),t->t.getPhone()));
		}else if(message.getSendType()==SendType.EMAIL.getType()){
			Collection<UserEmailVO> list=userDetailsDao.findAllUserEmailByUserIdIn(tos);
			targets=list.stream().collect(Collectors.toMap(t->t.getUserId(),t->t.getEmail()));
		}else if(message.getSendType()==SendType.MP.getType()){
			Collection<UserOpenIdVO> list=userOauthDao.findAllUserOpenIdByUserIdIn(tos);
			targets=list.stream().collect(Collectors.toMap(t->t.getUserId(),t->t.getOpenId()));
		}

		for(Long to : tos){
			try{
				UserMessage um=saveUserMessage(message,message.getFrom(),userDao.getOne(to),targets!=null?targets.get(to):null);
				sendUserMessage(um);
			}catch (Exception e) {
                e.printStackTrace();
			}
		}

		message.setStatus(MessageStatus.Sended.getStatus());
		message.setStatusRemark(MessageStatus.Sended.getStatusRemark());
		messageDao.save(message);

	}

	private void sendUserMessage(UserMessage um) throws Exception{
		if(um.getSendType()==SendType.SMS.getType()){
			sendSMS(um.getTarget(),um.getMessage());
		}else if(um.getSendType()==SendType.EMAIL.getType()){
			sendEmail(um.getTarget(),um.getMessage());
		}else if(um.getSendType()==SendType.PUSH.getType()){
            push(um.getTo().getId(),um.getMessage());
		}else if(um.getSendType()==SendType.MP.getType()){
            sendMP(um.getTarget(),um.getMessage());
		}
	}


    private void sendEmail(String email,Message message){
		// 发送邮件
		mailSender.sendHtmlMail(
				null,
				new String[]{email},
				message.getTitle(),
				message.getContent(),
				null
		);
	}

	private void sendSMS(String phone,Message message){
		// 发送短信
		smsService.send(phone,message.getType(),message.getContent());
	}

	private void push(Long userId,Message message){
		// 推送
		pushService.pushToAlias(
				new String[]{userId.toString()},
				message.getContent(),
				null,
				1
		);
	}

	private void sendMP(String openId,Message message) throws Exception{
		wxmp.sendMessage(openId,message.getTemplateId(),message.getContent());
	}




}
