package com.outmao.ebs.mall.order.domain.aspect;


import com.outmao.ebs.mall.order.common.annotation.SetUserStatsOrder;
import com.outmao.ebs.mall.order.common.data.UserStatsOrderSetter;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
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
public class SetUserStatsOrderAspect {

	@Autowired
	private OrderStatsDomain orderStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.order.common.annotation.SetUserStatsOrder)")
	public void SetUserStatsOrder() {

	}

	@AfterReturning(returning = "obj",value = "SetUserStatsOrder() && @annotation(ann)")
	public void SetUserStatsOrder(JoinPoint jp, Object obj, SetUserStatsOrder ann) {
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


	private void setList(List<? extends UserStatsOrderSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().map(t-> t.getUserId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<StatsOrderVO> stats=orderStatsDomain.getStatsOrderVOListByUserIdIn(idIn);
		Map<Long,StatsOrderVO> statsMap=stats.stream().collect(Collectors.toMap(t->t.getUserId(),t->t));

		list.stream().forEach(t->{
			StatsOrderVO s=statsMap.get(((UserStatsOrderSetter) t).getUserId());
			t.setStatsOrder(s);
		});
	}



}
