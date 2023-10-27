package com.outmao.ebs.hotel.domain.conver;


import com.outmao.ebs.hotel.common.annotation.SetSimpleHotel;
import com.outmao.ebs.hotel.common.data.SimpleHotelSetter;
import com.outmao.ebs.hotel.domain.HotelDomain;
import com.outmao.ebs.hotel.vo.SimpleHotelVO;
import com.outmao.ebs.user.common.annotation.SetSimpleUser;
import com.outmao.ebs.user.common.data.SimpleUserSetter;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.vo.SimpleUserVO;
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
public class SetSimpleHotelAspect {

	@Autowired
	private HotelDomain hotelDomain;


	@Pointcut("@annotation(com.outmao.ebs.hotel.common.annotation.SetSimpleHotel)")
	public void SetSimpleHotel() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleHotel() && @annotation(ann)")
	public void afterSetSimpleHotel(JoinPoint jp, Object obj, SetSimpleHotel ann) {
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


	private void setList(List<? extends SimpleHotelSetter> list){
		if(list==null||list.isEmpty())
			return;
		Collection<Long> hotelIdIn=list.stream().filter(t->((SimpleHotelSetter) t).getHotelId()!=null).map(t->((SimpleHotelSetter) t).getHotelId()).collect(Collectors.toList());
		if(hotelIdIn.isEmpty())
			return;
		List<SimpleHotelVO> hotels=hotelDomain.getSimpleHotelVOListByIdIn(hotelIdIn);
		Map<Long,SimpleHotelVO> hotelsMap=hotels.stream().collect(Collectors.toMap(t->t.getId(),t->t));
		list.stream().forEach(t->{
			t.setHotel(hotelsMap.get(t.getHotelId()));
		});
	}



}
