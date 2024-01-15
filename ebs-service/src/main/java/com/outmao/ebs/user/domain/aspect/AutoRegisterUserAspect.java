package com.outmao.ebs.user.domain.aspect;


import cn.jiguang.common.utils.StringUtils;
import com.outmao.ebs.user.common.annotation.AutoRegisterUser;
import com.outmao.ebs.user.common.data.RegisterUser;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AutoRegisterUserAspect {

	@Autowired
	private UserDomain userDomain;


	@Pointcut("@annotation(com.outmao.ebs.user.common.annotation.AutoRegisterUser)")
	public void AutoRegisterUser() {

	}

	@Before(value = "AutoRegisterUser() && @annotation(ann)")
	public void afterAutoRegisterUser(JoinPoint jp,AutoRegisterUser ann) {
		RegisterUser registerUser=(RegisterUser)jp.getArgs()[0];
		if(registerUser.getUserId()!=null)
			return;
		RegisterDTO registerDTO=registerUser.getRegisterRequest();
		if(registerDTO==null)
			return;
		User user=userDomain.getUserByUsername(registerDTO.getPrincipal());
		if(user==null){
			user=userDomain.registerUser(registerDTO);
		}else{
			if(!StringUtils.isEmpty(registerDTO.getCredentials())){
				userDomain.modifyUserPassword(user.getId(),null,registerDTO.getCredentials());
			}
		}
		registerUser.setUserId(user.getId());
	}



}
