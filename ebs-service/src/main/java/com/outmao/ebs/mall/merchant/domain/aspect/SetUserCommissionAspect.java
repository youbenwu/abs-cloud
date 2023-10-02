package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSetter;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Aspect
@Component
public class SetUserCommissionAspect {

	@Autowired
	UserCommissionDomain userCommissionDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission)")
	public void SetUserCommission() {

	}

	@AfterReturning(returning = "obj",value = "SetUserCommission() && @annotation(ann)")
	public void afterSetUserCommission(JoinPoint jp, Object obj, SetUserCommission ann) {
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


	private void setList(List<? extends UserCommissionSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t->t.getCommissionId()!=null).map(t-> t.getCommissionId()).collect(Collectors.toSet());
		List<UserCommissionVO> ms=userCommissionDomain.getUserCommissionVOListByIdIn(idIn);
		Map<Long,UserCommissionVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setCommission(msMap.get(t.getCommissionId()));
		});
	}



}
