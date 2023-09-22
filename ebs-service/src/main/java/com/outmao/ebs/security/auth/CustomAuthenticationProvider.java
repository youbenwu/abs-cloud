package com.outmao.ebs.security.auth;



import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	
	public CustomAuthenticationProvider(SecurityService userDetailsService, PasswordEncoder passwordEncoder) {
		setPasswordEncoder(passwordEncoder);
		setUserDetailsService(userDetailsService);
	}

	@Override
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
                                                         UserDetails user) {
		// 验证成功后设置状态
		SecurityService service = (SecurityService) this.getUserDetailsService();
		user=service.setAuthenticated((SecurityUser) user,true);
		CustomAuthenticationToken result = new CustomAuthenticationToken(user,
				authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
