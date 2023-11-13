package com.outmao.ebs.security.auth;



import com.outmao.ebs.security.configuration.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WxAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String session_key = obtainSessionKey(request);
		String unionid = obtainUnionid(request);
		String openid = obtainOpenid(request);
		String phone = obtainPhone(request);
		String nickname = obtainNickname(request);

		WxAuthenticationToken authRequest = new WxAuthenticationToken(session_key,unionid,openid,phone,nickname);
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	//String session_key,String unionid,String openid,String phone,String nickname
	protected String obtainSessionKey(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_WX_SESSION_KEY);
	}

	protected String obtainUnionid(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_WX_UNIONID);
	}

	protected String obtainOpenid(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_WX_OPENID);
	}

	protected String obtainPhone(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_MOBILE);
	}

	protected String obtainNickname(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_NICKNAME);
	}


}
