package com.outmao.ebs.security.handler;



import com.outmao.ebs.common.configuration.constant.Errors;
import com.outmao.ebs.common.util.JsonUtil;
import com.outmao.ebs.common.util.ServletUtil;
import com.outmao.ebs.common.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 请求接口时
 * 用户的权限错误时返回JSON错误信息。
 * 
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

	/**
	 * 
	 * 已登入用户的权限错误时返回JSON错误信息。
	 * 
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
			throws IOException {
		System.out.println("/*======AccessDeniedException=====*/");
		ServletUtil.responseText(response, JsonUtil.toJson(Result.result(Errors.FailureAuthorization)));
	}

	/**
	 *
	 * 未登入用户的权限错误时返回JSON错误信息。
	 * 
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {
		ServletUtil.responseText(response, JsonUtil.toJson(Result.result(Errors.FailureAuthentication)));
	}

}
