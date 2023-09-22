package com.outmao.ebs.security.auth;


import com.outmao.ebs.security.configuration.SecurityConstants;
import com.outmao.ebs.security.validate.ValidateCodeUtil;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhoneValidateCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String phone = obtainPhone(request);
		String validateCode = obtainValidateCode(request);
		if (phone == null)
			phone = "";
		if (validateCode == null)
			validateCode = "";
		phone = phone.trim();
		validateCode = validateCode.trim();
		try {
			ValidateCodeUtil.verify(phone,validateCode, request);
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}

		PhoneValidateCodeAuthenticationToken authRequest = new PhoneValidateCodeAuthenticationToken(phone, validateCode);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainPhone(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_MOBILE);
	}

	protected String obtainValidateCode(HttpServletRequest request) {
		return request.getParameter(SecurityConstants.PARAMETER_KEY_VERIFY_CODE);
	}

}
