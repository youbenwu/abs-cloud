package com.outmao.ebs.org.service.aspect;



import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.org.common.data.BindingOrg;
import com.outmao.ebs.org.dto.RegisterOrgDTO;
import com.outmao.ebs.org.entity.Org;
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

//    public static final int TYPE_SYSTEM=0;
//    public static final int TYPE_TENANT=1;
//    public static final int TYPE_DEPART=2;
//    public static final int TYPE_MERCHANT=3;
//    public static final int TYPE_STORE=4;
//    public static final int TYPE_SHOP=5;

	@Transactional
	@AfterReturning(returning = "bindingOrg",value = "BindingOrg()")
	public void afterBindingSubject(JoinPoint jp, BindingOrg bindingOrg) {
		if(bindingOrg.getOrgId()!=null)
			return;

        Item item=bindingOrg.toItem();
        RegisterOrgDTO orgDTO=new RegisterOrgDTO();
        if(bindingOrg.getParentOrgId()!=null){
            Org parentOrg=orgService.getOrgByTargetId(bindingOrg.getParentOrgId());
            orgDTO.setParentId(parentOrg.getId());
        }
        if(item.getType().equals("Merchant")){
            orgDTO.setType(Org.TYPE_MERCHANT);
        }else if(item.getType().equals("Store")){
            orgDTO.setType(Org.TYPE_STORE);
        }else if(item.getType().equals("Shop")){
            orgDTO.setType(Org.TYPE_SHOP);
        }else if(item.getType().equals("Hotel")){
            orgDTO.setType(Org.TYPE_HOTEL);
        }
        orgDTO.setTargetId(item.getId());
        orgDTO.setUserId(bindingOrg.getUserId());
        orgDTO.setName(item.getTitle());
        orgDTO.setContact(bindingOrg.getContact());
        Org org=orgService.registerOrg(orgDTO);
        bindingOrg.setOrgId(org.getId());

	}


}
