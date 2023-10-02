package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantDomain;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;


@Aspect
@Component
public class SetSimpleMerchantAspect {

	@Autowired
	MerchantDomain merchantDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchant)")
	public void SetSimpleMerchant() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleMerchant() && @annotation(ann)")
	public void afterSetSimpleMerchant(JoinPoint jp, Object obj, SetSimpleMerchant ann) {
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


	private void setList(List<? extends SimpleMerchantSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().map(t-> t.getMerchantId()).collect(Collectors.toSet());
		List<SimpleMerchantVO> ms=merchantDomain.getSimpleMerchantVOListByIdIn(idIn);
		Map<Long,SimpleMerchantVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setMerchant(msMap.get(t.getMerchantId()));
		});
	}



}
