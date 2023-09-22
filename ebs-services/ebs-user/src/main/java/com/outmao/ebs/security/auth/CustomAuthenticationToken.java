package com.outmao.ebs.security.auth;


import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken  extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public CustomAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	@Override
	public String getName() {
		if(isAuthenticated()){
			return ((SecurityUser)getPrincipal()).getSession().getSessionId().toString();
		}
		return super.getName();
	}

}
