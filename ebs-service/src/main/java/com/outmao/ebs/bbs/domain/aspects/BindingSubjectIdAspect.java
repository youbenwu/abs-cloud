package com.outmao.ebs.bbs.domain.aspects;



import com.outmao.ebs.bbs.common.data.BindingSubjectId;
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
public class BindingSubjectIdAspect {

	@Autowired
	private SubjectDomain subjectDomain;

	@Pointcut("@annotation(com.outmao.ebs.bbs.common.annotation.BindingSubjectId)")
	public void BindingSubject2() {

	}


	@Transactional
	@AfterReturning(returning = "bindingSubject",value = "BindingSubject2()")
	public void afterBindingSubject(JoinPoint jp, BindingSubjectId bindingSubject) {
		if(bindingSubject.getSubjectId()!=null)
			return;

		Subject subject=subjectDomain.saveSubject(bindingSubject.getUserId(),bindingSubject.toItem());

		bindingSubject.setSubjectId(subject.getId());

	}


}
