package com.outmao.ebs.mall.merchant.domain.aspect;



import com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantBroker;
import com.outmao.ebs.mall.merchant.common.data.SimpleMerchantBrokerSetter;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerDomain;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
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
public class SetSimpleMerchantBrokerAspect {

	@Autowired
    private MerchantBrokerDomain merchantMemberDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SetSimpleMerchantBroker)")
	public void SetSimpleMerchantBroker() {

	}

	@AfterReturning(returning = "obj",value = "SetSimpleMerchantBroker() && @annotation(ann)")
	public void afterSetSimpleMerchantMember(JoinPoint jp, Object obj, SetSimpleMerchantBroker ann) {
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


	private void setList(List<? extends SimpleMerchantBrokerSetter> list){
		if(list==null||list.isEmpty())
			return;
		Set<Long> idIn=list.stream().filter(t-> t.getBrokerId()!=null).map(t-> t.getBrokerId()).collect(Collectors.toSet());
		if(idIn.isEmpty())
			return;
		List<SimpleMerchantBrokerVO> ms=merchantMemberDomain.getSimpleMerchantBrokerVOListByIdIn(idIn);
		Map<Long, SimpleMerchantBrokerVO> msMap=ms.stream().collect(Collectors.toMap(t->t.getId(), t->t));
		list.stream().forEach(t->{
			t.setBroker(msMap.get(t.getBrokerId()));
		});
	}



}
