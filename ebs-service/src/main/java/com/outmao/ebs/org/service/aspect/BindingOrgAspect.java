package com.outmao.ebs.org.service.aspect;



import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.org.common.data.BindingOrg;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.entity.OrgType;
import com.outmao.ebs.org.service.OrgService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@Component
public class BindingOrgAspect {

	@Autowired
	private OrgService orgService;


	@Pointcut("@annotation(com.outmao.ebs.org.common.annotation.BindingOrg)")
	public void BindingOrg() {

	}

	@Transactional
	@AfterReturning(returning = "bindingOrg",value = "BindingOrg()")
	public void afterBindingOrg(JoinPoint jp, BindingOrg bindingOrg) {
		if(bindingOrg.getOrgId()!=null)
			return;

		Item item=bindingOrg.toItem();
		RegisterOrgDTO orgDTO=new RegisterOrgDTO();
		orgDTO.setType(OrgType.getType(item.getType()));
		orgDTO.setParentId(bindingOrg.getParentOrgId());
		orgDTO.setTargetId(item.getId());
		orgDTO.setUserId(bindingOrg.getUserId());
		orgDTO.setName(item.getTitle());
		orgDTO.setContact(bindingOrg.getContact());
		Org org=orgService.registerOrg(orgDTO);
		bindingOrg.setOrgId(org.getId());

	}


}
