package com.outmao.ebs.mall.merchant.domain.aspect;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantStats;
import com.outmao.ebs.mall.merchant.common.data.MerchantStatsSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantStatsDomain;
import com.outmao.ebs.mall.merchant.vo.MerchantStatsVO;
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
public class SetMerchantStatsAspect extends BaseDomain {

	@Autowired
	private MerchantStatsDomain merchantStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetMerchantStats)")
	public void SetMerchantStats() {

	}

	@AfterReturning(returning = "obj",value = "SetMerchantStats() && @annotation(ann)")
	public void SetMerchantStats(JoinPoint jp, Object obj, SetMerchantStats ann) {
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


	private void setList(List<? extends MerchantStatsSetter> list){
		if(list==null||list.isEmpty())
			return;
		List<MerchantStatsVO> statsList=merchantStatsDomain.getMerchantStatsVOListByMerchantIdIn(list.stream().map(t->((MerchantStatsSetter) t).getId()).collect(Collectors.toList()));

		Map<Long,MerchantStatsSetter> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		statsList.forEach(t->{
			MerchantStatsSetter vo=listMap.get(t.getMerchantId());
			vo.setStats(t);
		});

	}



}
