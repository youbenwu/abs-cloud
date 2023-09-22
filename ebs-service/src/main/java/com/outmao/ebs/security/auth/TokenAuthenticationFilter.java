package com.outmao.ebs.security.auth;



import com.outmao.ebs.security.configuration.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenAuthenticationFilter extends GenericFilterBean {

	private AuthenticationManager authenticationManager;

	public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = this.obtainToken((HttpServletRequest) request);
		// 不传TOKEN
		if (token == null || token.isEmpty()) {
			chain.doFilter(request, response);
			return;
		}

		// 其他过滤器已经认证通过了
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			chain.doFilter(request, response);
			return;
		}

		try {
			processTokenAuthentication(token);
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			chain.doFilter(request, response);
		}

	}

	// 将用户信息封装到TokenAuthentication（自定义用户信息类）中
	private void processTokenAuthentication(String token) throws AuthenticationException {
		Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	private Authentication tryToAuthenticateWithToken(String token) throws AuthenticationException {
		TokenAuthenticationToken tokenAuthentication = new TokenAuthenticationToken(token);
		return tryToAuthenticate(tokenAuthentication);
	}

	private Authentication tryToAuthenticate(Authentication requestAuthentication) throws AuthenticationException {
		// 找到配置的authenticationManager实现类provider进行验证返回充满信息的Authentication
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException("Unable to authenticate for provided credentials");
		}
		return responseAuthentication;
	}

	protected String obtainToken(HttpServletRequest request) {
		String token = request.getParameter(SecurityConstants.PARAMETER_KEY_TOKEN);
		if (token == null || token.isEmpty()) {
			token = request.getHeader(SecurityConstants.PARAMETER_KEY_TOKEN);
		}
		return token;
	}


}
