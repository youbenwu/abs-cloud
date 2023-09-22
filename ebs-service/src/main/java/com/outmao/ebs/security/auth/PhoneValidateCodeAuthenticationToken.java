package com.outmao.ebs.security.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PhoneValidateCodeAuthenticationToken extends CustomAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PhoneValidateCodeAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public PhoneValidateCodeAuthenticationToken(Object principal, Object credentials,
                                                Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}



}
