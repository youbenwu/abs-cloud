package com.outmao.ebs.common.services.eventBus.aspect;



import com.outmao.ebs.common.services.eventBus.EventService;
import com.outmao.ebs.common.services.eventBus.MethodEvent;
import com.outmao.ebs.common.services.eventBus.annotation.ActionEvent;
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
public class ActionEventAspect {

    @Autowired
	EventService eventService;

	@Pointcut("@annotation(com.outmao.ebs.common.services.eventBus.annotation.ActionEvent)")
	public void action() {

	}

	@AfterReturning(returning = "value", pointcut = "action()")
	public void afterAction(JoinPoint joinPoint, Object value) {

		MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		ActionEvent annotation = method.getAnnotation(ActionEvent.class);

		Class[] actions=annotation.value();

		for (Class action:actions){
			MethodEvent event=new MethodEvent();
			event.setAction(action);
			event.setArgs(joinPoint.getArgs());
			event.setReturning(value);

			if(annotation.async()){
				eventService.postAsync(event);
			}else {
				eventService.post(event);
			}

		}

	}



}
