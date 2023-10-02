package com.outmao.ebs.mall.product.domain.aspect;


import com.outmao.ebs.mall.product.common.annotation.SetMiniProduct;
import com.outmao.ebs.mall.product.common.data.MiniProductSetter;
import com.outmao.ebs.mall.product.domain.ProductDomain;
import com.outmao.ebs.mall.product.vo.MiniProductVO;
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
public class SetMiniProductAspect {

	@Autowired
	private ProductDomain productDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.product.common.annotation.SetMiniProduct)")
	public void SetMiniProduct() {

	}

	@AfterReturning(returning = "obj",value = "SetMiniProduct() && @annotation(ann)")
	public void afterSetMiniProduct(JoinPoint jp, Object obj, SetMiniProduct ann) {
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


	private void setList(List<? extends MiniProductSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t->t.getProductId()!=null).map(t-> t.getProductId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<MiniProductVO> vos=productDomain.getMiniProductVOListByIdIn(idIn);
		Map<Long,MiniProductVO> msMap=vos.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			 t.setProduct(msMap.get(t.getProductId()));
		});
	}



}
