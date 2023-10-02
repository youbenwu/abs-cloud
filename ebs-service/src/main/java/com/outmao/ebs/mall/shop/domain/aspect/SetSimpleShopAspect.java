package com.outmao.ebs.mall.shop.domain.aspect;


import com.outmao.ebs.mall.shop.common.annotation.SetSimpleShop;
import com.outmao.ebs.mall.shop.common.data.SimpleShopSetter;
import com.outmao.ebs.mall.shop.domain.ShopDomain;
import com.outmao.ebs.mall.shop.vo.SimpleShopVO;
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
public class SetSimpleShopAspect {

	@Autowired
	ShopDomain shopDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.shop.common.annotation.SetSimpleShop)")
	public void SetSimpleShop() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleShop() && @annotation(ann)")
	public void SetSimpleShop(JoinPoint jp, Object obj, SetSimpleShop ann) {
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


	private void setList(List<? extends SimpleShopSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().map(t-> ((SimpleShopSetter) t).getShopId()).collect(Collectors.toSet());
		List<SimpleShopVO> ms=shopDomain.getSimpleShopVOListByIdIn(idIn);
		Map<Long,SimpleShopVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			((SimpleShopSetter) t).setShop(msMap.get(((SimpleShopSetter) t).getShopId()));
		});
	}



}
