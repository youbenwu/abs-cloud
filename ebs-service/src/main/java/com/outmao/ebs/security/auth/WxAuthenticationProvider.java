package com.outmao.ebs.security.auth;


import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class WxAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


	private SecurityService userDetailsService;

	public WxAuthenticationProvider(SecurityService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		try {
			WxAuthenticationToken token=(WxAuthenticationToken)authentication;
			UserDetails loadedUser = userDetailsService.loadUserOrRegisterByWx(token.getSession_key(),token.getUnionid(),token.getOpenid(),token.getPhone(),token.getNickname());
			if (loadedUser == null) {
				throw new InternalAuthenticationServiceException(
						"UserDetailsService returned null, which is an interface contract violation");
			}
			return loadedUser;
		} catch (UsernameNotFoundException ex) {
			throw ex;
		} catch (InternalAuthenticationServiceException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
		user=userDetailsService.setAuthenticated((SecurityUser) user,true);
		WxAuthenticationToken result = new WxAuthenticationToken(user,authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return WxAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
