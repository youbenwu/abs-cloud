package com.outmao.ebs.mall.merchant.domain.aspect;



import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantCustomer;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantCustomerSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
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
public class SetSimpleMerchantCustomerAspect {

	@Autowired
	private MerchantCustomerDomain merchantCustomerDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantCustomer)")
	public void SetSimpleMerchantCustomer() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleMerchantCustomer() && @annotation(ann)")
	public void SetSimpleMerchantCustomer(JoinPoint jp, Object obj, SetSimpleMerchantCustomer ann) {
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


	private void setList(List<? extends SimpleMerchantCustomerSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t-> ((SimpleMerchantCustomerSetter) t).getCustomerId()!=null).map(t-> ((SimpleMerchantCustomerSetter) t).getCustomerId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<SimpleMerchantCustomerVO> ms=merchantCustomerDomain.getSimpleMerchantCustomerVOByIdIn(idIn);
		Map<Long,SimpleMerchantCustomerVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(), t->t));
		list.stream().forEach(t->{
			((SimpleMerchantCustomerSetter) t).setCustomer(msMap.get(((SimpleMerchantCustomerSetter) t).getCustomerId()));
		});
	}



}
