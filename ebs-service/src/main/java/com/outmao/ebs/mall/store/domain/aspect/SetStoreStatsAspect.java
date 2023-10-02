package com.outmao.ebs.mall.store.domain.aspect;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.store.common.annotation.SetStoreStats;
import com.outmao.ebs.mall.store.common.data.StoreStatsSetter;
import com.outmao.ebs.mall.store.domain.StoreStatsDomain;
import com.outmao.ebs.mall.store.vo.StoreStatsVO;
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
public class SetStoreStatsAspect extends BaseDomain {

	@Autowired
	private StoreStatsDomain storeStatsDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.store.common.annotation.SetStoreStats)")
	public void SetStoreStats() {

	}

	@AfterReturning(returning = "obj",value = "SetStoreStats() && @annotation(ann)")
	public void SetStoreStats(JoinPoint jp, Object obj, SetStoreStats ann) {
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


	private void setList(List<? extends StoreStatsSetter> list){
		if(list==null||list.isEmpty())
			return;
		List<StoreStatsVO> statsList=storeStatsDomain.getStoreStatsVOListByStoreIdIn(list.stream().map(t->t.getId()).collect(Collectors.toList()));

		Map<Long,StoreStatsSetter> listMap=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

		statsList.forEach(t->{
			StoreStatsSetter vo=listMap.get(t.getStoreId());
			vo.setStats(t);
		});

	}



}
