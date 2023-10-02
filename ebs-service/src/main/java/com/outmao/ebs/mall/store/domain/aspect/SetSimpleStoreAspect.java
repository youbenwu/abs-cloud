package com.outmao.ebs.mall.store.domain.aspect;


import com.outmao.ebs.mall.store.common.annotation.SetSimpleStore;
import com.outmao.ebs.mall.store.common.data.SimpleStoreSetter;
import com.outmao.ebs.mall.store.domain.StoreDomain;
import com.outmao.ebs.mall.store.vo.SimpleStoreVO;
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
public class SetSimpleStoreAspect {

	@Autowired
	private StoreDomain storeDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.store.common.annotation.SetSimpleStore)")
	public void SetSimpleStore() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleStore() && @annotation(ann)")
	public void afterSetSimpleStore(JoinPoint jp, Object obj, SetSimpleStore ann) {
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


	private void setList(List<? extends SimpleStoreSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t->t.getStoreId()!=null).map(t-> t.getStoreId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<SimpleStoreVO> ms=storeDomain.getSimpleStoreVOListByIdIn(idIn);
		Map<Long,SimpleStoreVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			 t.setStore(msMap.get(t.getStoreId()));
		});
	}



}
