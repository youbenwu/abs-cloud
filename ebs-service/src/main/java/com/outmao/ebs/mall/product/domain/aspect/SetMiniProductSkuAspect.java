package com.outmao.ebs.mall.product.domain.aspect;


import com.outmao.ebs.mall.product.common.annotation.SetMiniProductSku;
import com.outmao.ebs.mall.product.common.data.MiniProductSkuSetter;
import com.outmao.ebs.mall.product.domain.ProductDomain;
import com.outmao.ebs.mall.product.vo.MiniProductSkuVO;
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
public class SetMiniProductSkuAspect {

	@Autowired
	private ProductDomain productDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.product.common.annotation.SetMiniProductSku)")
	public void SetMiniProductSku() {

	}

	@AfterReturning(returning = "obj",value = "SetMiniProductSku() && @annotation(ann)")
	public void afterSetMiniProductSku(JoinPoint jp, Object obj, SetMiniProductSku ann) {
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


	private void setList(List<? extends MiniProductSkuSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t->t.getSkuId()!=null).map(t-> t.getSkuId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<MiniProductSkuVO> vos=productDomain.getMiniProductSkuVOListByIdIn(idIn);
		Map<Long,MiniProductSkuVO> msMap=vos.stream().collect(Collectors.toMap(t->t.getSkuId(),t->t));
		list.stream().forEach(t->{
			 t.setSku(msMap.get(t.getSkuId()));
		});
	}



}
