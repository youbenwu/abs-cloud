package com.outmao.ebs.bbs.domain.aspects;


import com.outmao.ebs.bbs.common.data.BindingSubject;
import com.outmao.ebs.bbs.domain.SubjectDomain;
import com.outmao.ebs.bbs.entity.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class BindingSubjectAspect {

	@Autowired
	private SubjectDomain subjectDomain;

	@Pointcut("@annotation(com.outmao.ebs.bbs.common.annotation.BindingSubject)")
	public void BindingSubject() {

	}


	@Transactional
	@AfterReturning(returning = "bindingSubject",value = "BindingSubject()")
	public void afterBindingSubject(JoinPoint jp, BindingSubject bindingSubject) {
		if(bindingSubject.getSubject()!=null)
			return;

		Subject subject=subjectDomain.saveSubject(bindingSubject.getUser().getId(),bindingSubject.toItem());

		bindingSubject.setSubject(subject);

	}


}
