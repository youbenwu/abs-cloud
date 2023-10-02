package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.merchant.common.annotation.SetMerchantCustomerStats;
import com.outmao.ebs.mall.merchant.common.data.MerchantCustomerStatsSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerStatsDomain;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerStatsVO;
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
public class SetMerchantCustomerStatsAspect extends BaseDomain {

	@Autowired
	private MerchantCustomerStatsDomain merchantCustomerStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetMerchantCustomerStats)")
	public void SetMerchantCustomerStats() {

	}

	@AfterReturning(returning = "obj",value = "SetMerchantCustomerStats() && @annotation(ann)")
	public void SetMerchantCustomerStats(JoinPoint jp, Object obj, SetMerchantCustomerStats ann) {
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


	private void setList(List<? extends MerchantCustomerStatsSetter> list){
		if(list==null||list.isEmpty())
			return;
		List<MerchantCustomerStatsVO> statsList=merchantCustomerStatsDomain.getMerchantCustomerStatsVOListByCustomerIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));

		Map<Long,MerchantCustomerStatsSetter> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		statsList.forEach(t->{
			MerchantCustomerStatsSetter vo=listMap.get(t.getCustomerId());
			vo.setStats(t);
		});

	}



}
