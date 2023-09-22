package com.outmao.ebs.security.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthenticationToken extends CustomAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String token;

	public TokenAuthenticationToken(String token) {
		super(null, null);
		this.token=token;
	}

	public TokenAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
