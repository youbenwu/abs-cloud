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

		//String session_code,String phone_code
		String session_code = obtainSessionCode(request);
		String phone_code = obtainPhoneCode(request);

		WxAuthenticationToken authRequest = new WxAuthenticationToken(session_code,phone_code);
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainSessionCode(HttpServletRequest request) {
		return request.getParameter("session_code");
	}

	protected String obtainPhoneCode(HttpServletRequest request) {
		return request.getParameter("phone_code");
	}




}
