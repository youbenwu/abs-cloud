package com.outmao.ebs.user.domain.aspect;


import com.outmao.ebs.user.common.annotation.SetContactUser;
import com.outmao.ebs.user.common.data.ContactUserSetter;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.vo.ContactUserVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Aspect
@Component
public class SetContactUserAspect {

	@Autowired
	private UserDomain userDomain;


	@Pointcut("@annotation(com.outmao.ebs.user.common.annotation.SetContactUser)")
	public void SetContactUser() {

	}

	@AfterReturning(returning = "obj",value = "SetContactUser() && @annotation(ann)")
	public void afterSetContactUser(JoinPoint jp, Object obj, SetContactUser ann) {
        if(obj==null)
        	return;
		if(obj instanceof Page){
			setList(((Page) obj).getContent());
		}else if(obj instanceof List){
			setList((List)obj);
		}else{
			List list=new ArrayList<>(1);
			list.add(obj);
			setList(list);
		}

	}


	private void setList(List<? extends ContactUserSetter> list){
		if(list==null||list.isEmpty())
			return;
		Collection<Long> userIdIn=list.stream().filter(t->t.getUserId()!=null).map(t->t.getUserId()).collect(Collectors.toList());
		if(userIdIn.isEmpty())
			return;
		List<ContactUserVO> users=userDomain.getContactUserVOListByIdIn(userIdIn);
		Map<Long,ContactUserVO> usersMap=users.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setUser(usersMap.get(t.getUserId()));
		});
	}



}
