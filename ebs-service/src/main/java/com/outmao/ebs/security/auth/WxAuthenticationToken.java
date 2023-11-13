package com.outmao.ebs.security.auth;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class WxAuthenticationToken extends CustomAuthenticationToken {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//String session_key,String unionid,String openid,String phone,String nickname
	private String session_key;
	private String unionid;
	private String openid;
	private String phone;
	private String nickname;

	public WxAuthenticationToken(String session_key,String unionid,String openid,String phone,String nickname) {
		super(null, null);
		this.session_key=session_key;
		this.unionid=unionid;
		this.openid=openid;
		this.phone=phone;
		this.nickname=nickname;
	}

	public WxAuthenticationToken(Object principal, Object credentials,
                                 Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}


	public String getSession_key() {
		return session_key;
	}

	public String getUnionid() {
		return unionid;
	}

	public String getOpenid() {
		return openid;
	}

	public String getPhone() {
		return phone;
	}

	public String getNickname() {
		return nickname;
	}
}
