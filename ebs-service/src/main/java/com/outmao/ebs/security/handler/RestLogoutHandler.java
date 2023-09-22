package com.outmao.ebs.security.handler;



import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.ServletUtil;
import com.outmao.ebs.common.vo.Result;
import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestLogoutHandler implements LogoutSuccessHandler, LogoutHandler {

	@Autowired
	private SecurityService securityUserService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("/* onLogoutSuccess */");
		ServletUtil.responseText(response, JsonUtil.toJson(Result.successResult(null,"已注销")));
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("/* logout */");
		if (authentication != null) {
			SecurityUser user = (SecurityUser) authentication.getPrincipal();
			securityUserService.setAuthenticated(user, false);
		}
	}

}
