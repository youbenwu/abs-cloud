package com.outmao.ebs.message.common.aspect;



import com.outmao.ebs.message.common.annotation.SendMessage;
import com.outmao.ebs.message.dto.SendMessageByTypeDTO;
import com.outmao.ebs.message.service.MessageService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class SendMessageAspect {


	@Autowired
	private MessageService messageService;


	@Pointcut("@annotation(com.outmao.ebs.message.common.annotation.SendMessage)")
	public void SendMessage() { }

	@AfterReturning(returning = "value", pointcut = "SendMessage()")
	public void SendMessage(JoinPoint joinPoint, Object value) {

		SendMessage annotation = getAnnotation(joinPoint);

		String type=annotation.type();

		SendMessageByTypeDTO request=new SendMessageByTypeDTO();
		request.setType(type);

		messageService.sendMessageAsync(request);

	}


	private SendMessage getAnnotation(JoinPoint joinPoint){
		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		SendMessage annotation = method.getAnnotation(SendMessage.class);
		return annotation;
	}


}
