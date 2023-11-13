package com.outmao.ebs.security.auth;


import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class WxAuthenticationToken extends CustomAuthenticationToken {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//String session_code,String phone_code
	private String session_code;
	private String phone_code;

	public WxAuthenticationToken(String session_code,String phone_code) {
		super(null, null);
		this.session_code=session_code;
		this.phone_code=phone_code;
	}

	public WxAuthenticationToken(Object principal, Object credentials,
                                 Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}


	public String getSession_code() {
		return session_code;
	}

	public String getPhone_code() {
		return phone_code;
	}
}
