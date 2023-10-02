package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantPartnerStats;
import com.outmao.ebs.mall.merchant.common.data.MerchantPartnerStatsSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantPartnerStatsDomain;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerStatsVO;
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
import java.util.stream.Collectors;


@Aspect
@Component
public class SetMerchantPartnerStatsAspect extends BaseDomain {

	@Autowired
	private MerchantPartnerStatsDomain merchantPartnerStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetMerchantPartnerStats)")
	public void SetMerchantPartnerStats() {

	}

	@AfterReturning(returning = "obj",value = "SetMerchantPartnerStats() && @annotation(ann)")
	public void SetMerchantPartnerStats(JoinPoint jp, Object obj, SetMerchantPartnerStats ann) {
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


	private void setList(List<? extends MerchantPartnerStatsSetter> list){
		if(list==null||list.isEmpty())
			return;
		List<MerchantPartnerStatsVO> statsList=merchantPartnerStatsDomain.getMerchantPartnerStatsVOByPartnerIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));

		Map<Long,MerchantPartnerStatsSetter> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		statsList.forEach(t->{
			MerchantPartnerStatsSetter vo=listMap.get(t.getPartnerId());
			vo.setStats(t);
		});

	}



}
