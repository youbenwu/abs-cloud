package com.outmao.ebs.security.auth;



import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class PhoneValidateCodeAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private boolean autoRegister;
	private SecurityService userDetailsService;

	public PhoneValidateCodeAuthenticationProvider(SecurityService userDetailsService, boolean autoRegister) {
		this.autoRegister = autoRegister;
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
			UserDetails loadedUser =autoRegister?userDetailsService.loadUserOrRegisterByPhone(username)
					: this.getUserDetailsService().loadUserByUsername(username);
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
		// 验证成功后设置状态,重新获取用户信息
		user = userDetailsService.setAuthenticated((SecurityUser) user, true);
		PhoneValidateCodeAuthenticationToken result = new PhoneValidateCodeAuthenticationToken(user,
				authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PhoneValidateCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}


}
