package com.outmao.ebs.mall.merchant.domain.aspect;


import com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSaver;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSaverRequest;
import com.outmao.ebs.mall.merchant.common.data.UserCommissionSetter;
import com.outmao.ebs.mall.merchant.domain.UserCommissionDomain;
import com.outmao.ebs.mall.merchant.dto.UserCommissionDTO;
import com.outmao.ebs.mall.merchant.entity.UserCommission;
import com.outmao.ebs.mall.merchant.vo.UserCommissionVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
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
public class SaveUserCommissionAspect {

	@Autowired
	private UserCommissionDomain userCommissionDomain;


	@Pointcut("@annotation(com.outmao.ebs.mall.merchant.common.annotation.SaveUserCommission)")
	public void SaveUserCommission() {

	}

	@AfterReturning(returning = "obj",value = "SaveUserCommission() && @annotation(ann)")
	public void afterSaveUserCommission(JoinPoint jp, UserCommissionSaver obj, SetUserCommission ann) {

		if(obj.getCommissionId()!=null)
			return;

		UserCommissionSaverRequest dto=(UserCommissionSaverRequest)jp.getArgs()[0];
		UserCommissionDTO commissionDTO=new UserCommissionDTO();
		BeanUtils.copyProperties(dto,commissionDTO);
		commissionDTO.setType(dto.getCommissionType());
		commissionDTO.setTargetId(obj.getId());

		UserCommission userCommission=userCommissionDomain.saveUserCommission(commissionDTO);
        obj.setCommissionId(userCommission.getId());



	}






}
