package com.outmao.ebs.mall.inquiry.domain.aspect;


import com.outmao.ebs.mall.inquiry.common.annotation.SetUserStatsInquiry;
import com.outmao.ebs.mall.inquiry.common.data.UserStatsInquirySetter;
import com.outmao.ebs.mall.inquiry.domain.InquiryDomain;
import com.outmao.ebs.mall.inquiry.vo.StatsInquiryVO;
import com.outmao.ebs.mall.order.common.data.UserStatsOrderSetter;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
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
public class SetUserStatsInquiryAspect {

	@Autowired
	InquiryDomain inquiryDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.inquiry.common.annotation.SetUserStatsInquiry)")
	public void SetUserInquiryCount() {

	}

	@AfterReturning(returning = "obj",value = "SetUserInquiryCount() && @annotation(ann)")
	public void SetUserInquiryCount(JoinPoint jp, Object obj, SetUserStatsInquiry ann) {

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


	private void setList(List<? extends UserStatsInquirySetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().map(t->t.getUserId()).collect(Collectors.toSet());
        if(idIn.isEmpty())
        	return;
		List<StatsInquiryVO> stats=inquiryDomain.getStatsInquiryVOListByUserIdIn(idIn);
		Map<Long,StatsInquiryVO> statsMap=stats.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));

		list.stream().forEach(t->{
			StatsInquiryVO s=statsMap.get(t.getUserId());
			t.setStatsInquiry(s);
		});
	}



}
