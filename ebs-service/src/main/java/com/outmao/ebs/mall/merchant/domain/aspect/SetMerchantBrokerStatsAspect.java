package com.outmao.ebs.mall.merchant.domain.aspect;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantBrokerStats;
import com.outmao.ebs.mall.merchant.common.data.MerchantBrokerStatsSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerStatsDomain;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerStatsVO;
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
public class SetMerchantBrokerStatsAspect extends BaseDomain {

	@Autowired
	private MerchantBrokerStatsDomain merchantBrokerStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetMerchantBrokerStats)")
	public void SetMerchantBrokerStats() {

	}

	@AfterReturning(returning = "obj",value = "SetMerchantBrokerStats() && @annotation(ann)")
	public void SetMerchantBrokerStats(JoinPoint jp, Object obj, SetMerchantBrokerStats ann) {
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


	private void setList(List<? extends MerchantBrokerStatsSetter> list){
		if(list==null||list.isEmpty())
			return;
		List<MerchantBrokerStatsVO> statsList=merchantBrokerStatsDomain.getMerchantBrokerStatsVOListByBrokerIdIn(list.stream().map(t->((MerchantBrokerStatsSetter) t).getId()).collect(Collectors.toList()));

		Map<Long,MerchantBrokerStatsSetter> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		statsList.forEach(t->{
			MerchantBrokerStatsSetter vo=listMap.get(t.getBrokerId());
			vo.setStats(t);
		});

	}



}
