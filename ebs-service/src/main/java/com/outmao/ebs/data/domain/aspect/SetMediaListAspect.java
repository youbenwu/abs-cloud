package com.outmao.ebs.data.domain.aspect;



import com.outmao.ebs.data.common.annotation.SetMediaList;
import com.outmao.ebs.data.common.data.MediaListSetter;
import com.outmao.ebs.data.domain.MediaDomain;
import com.outmao.ebs.data.vo.MediaVO;
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
public class SetMediaListAspect {

	@Autowired
	MediaDomain mediaDomain;


	@Pointcut("@annotation(com.outmao.ebs.data.common.annotation.SetMediaList)")
	public void MediaListInject() {

	}

	@AfterReturning(returning = "obj",value = "MediaListInject() && @annotation(ann)")
	public void afterMediaListInject(JoinPoint jp, Object obj, SetMediaList ann) {

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


	private void injectList(List<? extends MediaListSetter> list){
		if(list==null||list.isEmpty())
			return;
		Collection<Long> idIn=new HashSet<>();
		list.forEach(t->{
			idIn.addAll(t.getMedias().stream().map(m->m.getMediaId()).collect(Collectors.toList()));
		});
		List<MediaVO> medias=mediaDomain.getMediaVOList(idIn);
		Map<Long,MediaVO> dataMap=medias.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.getMedias().forEach(m->{
				m.setMedia(dataMap.get(m.getMediaId()));
			});
		});
	}



}
