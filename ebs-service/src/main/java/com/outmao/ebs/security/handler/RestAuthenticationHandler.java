package com.outmao.ebs.security.handler;



import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.ServletUtil;
import com.outmao.ebs.common.vo.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 用户认证结果处理。
 * 
 */

public class RestAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException {
		System.out.println("/* onAuthenticationSuccess */");
		ServletUtil.responseText(response, JsonUtil.toJson(Result.successResult(auth.getPrincipal(),"登录成功")));

	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
		System.out.println("/* onAuthenticationFailure */");
		ServletUtil.responseText(response, JsonUtil.toJson(Result.failureResult(e.getMessage())));

	}

}
