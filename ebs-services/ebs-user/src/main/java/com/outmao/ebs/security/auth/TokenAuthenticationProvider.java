package com.outmao.ebs.security.auth;



import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationProvider implements AuthenticationProvider {

	private SecurityService userDetailsService;

	public TokenAuthenticationProvider(SecurityService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// 验证token逻辑
		SecurityUser user =getUserDetailsService().loadUserByToken(((TokenAuthenticationToken)authentication).getToken());
		if(user!=null) {
			return new TokenAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public SecurityService getUserDetailsService() {
		return userDetailsService;
	}
}
