package com.outmao.ebs.data.domain.aspect;


import com.outmao.ebs.data.common.annotation.SetMedia;
import com.outmao.ebs.data.common.data.MediaSetter;
import com.outmao.ebs.data.domain.MediaDomain;
import com.outmao.ebs.data.vo.MediaVO;
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
public class SetMediaAspect {

	@Autowired
	MediaDomain mediaDomain;


	@Pointcut("@annotation(com.outmao.ebs.data.common.annotation.SetMedia)")
	public void MediaInject() {

	}

	@AfterReturning(returning = "obj",value = "MediaInject() && @annotation(ann)")
	public void afterMediaInject(JoinPoint jp, Object obj, SetMedia ann) {

		if(obj instanceof Page){
			injectList(((Page) obj).getContent());
		}else if(obj instanceof List){
			injectList((List)obj);
		}else{
			List list=new ArrayList<>(1);
			list.add(obj);
			injectList(list);
		}

	}


	private void injectList(List<? extends MediaSetter> list){
		if(list==null||list.isEmpty())
			return;
		Collection<Long> idIn=list.stream().map(t->t.getMediaId()).collect(Collectors.toList());
		List<MediaVO> medias=mediaDomain.getMediaVOList(idIn);
		Map<Long,MediaVO> usersMap=medias.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setMedia(usersMap.get(t.getMediaId()));
		});
	}



}
