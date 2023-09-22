package com.outmao.ebs.security.auth;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WeixinAuthenticationToken extends CustomAuthenticationToken {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	public WeixinAuthenticationToken(String code) {
		super(null, null);
		this.code=code;
	}

	public WeixinAuthenticationToken(Object principal, Object credentials,
                                     Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
