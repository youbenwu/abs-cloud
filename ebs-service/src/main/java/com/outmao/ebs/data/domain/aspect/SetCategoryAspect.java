package com.outmao.ebs.data.domain.aspect;


import com.outmao.ebs.data.common.annotation.SetCategory;
import com.outmao.ebs.data.common.data.CategorySetter;
import com.outmao.ebs.data.domain.CategoryDomain;
import com.outmao.ebs.data.vo.CategoryVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Aspect
@Component
public class SetCategoryAspect {

	@Autowired
	CategoryDomain categoryDomain;


	@Pointcut("@annotation(com.outmao.ebs.data.common.annotation.SetCategory)")
	public void SetCategory() {

	}

	@AfterReturning(returning = "obj",value = "SetCategory() && @annotation(ann)")
	public void afterSetCategory(JoinPoint jp, Object obj, SetCategory ann) {
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


	private void setList(List<? extends CategorySetter> list){
		if(list==null||list.isEmpty())
			return;
		Collection<Long> ids=list.stream().filter(t->(t.getCategoryId()!=null)).map(t-> t.getCategoryId()).collect(Collectors.toList());
		if(ids.isEmpty())
			return;
		List<CategoryVO> items=categoryDomain.getCategoryVOListByIdIn(ids);
		Map<Long,CategoryVO> map=items.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setCategory(map.get((t.getCategoryId())));
		});
	}



}
