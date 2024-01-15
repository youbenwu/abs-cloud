package com.outmao.ebs.mall.merchant.service.aspect;


import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.mall.merchant.common.data.BindingMerchant;
import com.outmao.ebs.mall.merchant.dto.MerchantDTO;
import com.outmao.ebs.mall.merchant.dto.SetMerchantStatusDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.org.dto.OrgTypeDTO;
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
public class BindingMerchantAspect {

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private OrgService orgService;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.BindingMerchant)")
	public void BindingMerchant() {

	}

	@Transactional
	@AfterReturning(returning = "bindingMerchant",value = "BindingMerchant()")
	public void afterBindingMerchant(JoinPoint jp, BindingMerchant bindingMerchant) {
		if(bindingMerchant.getMerchantId()!=null)
			return;

		Merchant merchant=merchantService.getMerchantByUserId(bindingMerchant.getUserId());

		Item item=bindingMerchant.toItem();

		if(merchant==null){

			MerchantDTO dto=new MerchantDTO();
			dto.setUserId(bindingMerchant.getUserId());
			dto.setName(item.getTitle());
			dto.setIntro(item.getContent());
			dto.setContact(bindingMerchant.getContact());
			merchant=merchantService.saveMerchant(dto);
			merchantService.setMerchantStatus(new SetMerchantStatusDTO(
					merchant.getId(),
					Status.NORMAL.getStatus(),
					Status.NORMAL.getStatusRemark())
			);
		}

		OrgTypeDTO typeDTO=new OrgTypeDTO();
		typeDTO.setOrgId(merchant.getOrgId());
		typeDTO.setTargetId(item.getId());
		typeDTO.setName(item.getType());
		typeDTO.setType(OrgType.getType(item.getType()));
		orgService.saveOrgType(typeDTO);

		bindingMerchant.setMerchantId(merchant.getId());
		bindingMerchant.setOrgId(merchant.getOrgId());
		bindingMerchant.setShopId(merchant.getShopId());
		bindingMerchant.setWalletId(merchant.getWalletId());


	}




}
