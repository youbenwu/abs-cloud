package com.outmao.ebs.security.configuration;




import com.outmao.ebs.common.util.RequestMappingUtil;
import com.outmao.ebs.security.access.SecurityPermissionEvaluatorImpl;
import com.outmao.ebs.security.auth.*;
import com.outmao.ebs.security.handler.RestAccessDeniedHandler;
import com.outmao.ebs.security.handler.RestAuthenticationHandler;
import com.outmao.ebs.security.handler.RestLogoutHandler;
import com.outmao.ebs.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class MultiHttpSecurityConfiguration {

	@Autowired
	private SecurityService securityUserService;


	/**
	 * 注入自定义PermissionEvaluator
	 */
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setPermissionEvaluator(new SecurityPermissionEvaluatorImpl());
		return handler;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(securityUserService, passwordEncoder());
	}

	@Bean
	public TokenAuthenticationProvider tokenAuthenticationProvider() {
		return new TokenAuthenticationProvider(securityUserService);
	}


	@Bean
	public PhoneValidateCodeAuthenticationProvider phoneValidateCodeAuthenticationProvider() {
		return new PhoneValidateCodeAuthenticationProvider(securityUserService, true);
	}

	@Bean
	public WeixinAuthenticationProvider weixinAuthenticationProvider() {
		return new WeixinAuthenticationProvider(securityUserService);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
		// token自动登录
		auth.authenticationProvider(tokenAuthenticationProvider());
		// 手机号登录
		auth.authenticationProvider(phoneValidateCodeAuthenticationProvider());
		// weixin登录
		auth.authenticationProvider(weixinAuthenticationProvider());
		// 指定密码加密所使用的加密器为passwordEncoder()
		// 需要将密码加密后写入数据库
		auth.userDetailsService(securityUserService).passwordEncoder(passwordEncoder());
		auth.eraseCredentials(false);
	}

	@Configuration
	@Order(1)
	public static class RestWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		List<String> permitAllPaths = new ArrayList<>();

		@Resource
		private RequestMappingHandlerMapping requestMappingHandlerMapping;

		@Override
		@Bean // share AuthenticationManager for web and oauth
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean
		public RestAccessDeniedHandler accessDeniedHandler() {
			return new RestAccessDeniedHandler();
		}

		@Bean
		public RestAuthenticationHandler authenticationHandler() {
			return new RestAuthenticationHandler();
		}

		@Bean
		public RestLogoutHandler logoutHandler() {
			return new RestLogoutHandler();
		}

		// 注册自定义的UsernamePasswordAuthenticationFilter

		@Bean
		CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
			CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
			filter.setFilterProcessesUrl("/api/user/login");
			filter.setAuthenticationSuccessHandler(authenticationHandler());
			filter.setAuthenticationFailureHandler(authenticationHandler());
			// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
			filter.setAuthenticationManager(authenticationManagerBean());
			return filter;
		}


		// 注册自定义的UsernamePasswordAuthenticationFilter
		@Bean
		PhoneValidateCodeAuthenticationFilter phoneValidateCodeAuthenticationFilter() throws Exception {
			PhoneValidateCodeAuthenticationFilter filter = new PhoneValidateCodeAuthenticationFilter();
			filter.setFilterProcessesUrl("/api/user/login/phone");
			filter.setAuthenticationSuccessHandler(authenticationHandler());
			filter.setAuthenticationFailureHandler(authenticationHandler());
			// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
			filter.setAuthenticationManager(authenticationManagerBean());
			return filter;
		}

		// 注册自定义的UsernamePasswordAuthenticationFilter
		@Bean
        TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
			TokenAuthenticationFilter filter = new TokenAuthenticationFilter(authenticationManagerBean());
			return filter;
		}

		// 注册自定义的WeixinAuthenticationFilter
		@Bean
        WeixinAuthenticationFilter weixinAuthenticationFilter() throws Exception {
			WeixinAuthenticationFilter filter = new WeixinAuthenticationFilter();
			filter.setFilterProcessesUrl("/api/user/login/weChatCode");
			filter.setAuthenticationSuccessHandler(authenticationHandler());
			filter.setAuthenticationFailureHandler(authenticationHandler());
			// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
			filter.setAuthenticationManager(authenticationManagerBean());
			return filter;
		}

		private void initPermitAll() {
			// 查找permitAll注解
			Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
			for (RequestMappingInfo info : map.keySet()) {
				PreAuthorize pre = map.get(info).getMethod().getAnnotation(PreAuthorize.class);
				if (pre != null && pre.value().contains("permitAll")) {
					String path = RequestMappingUtil.getPath(map.get(info));
					permitAllPaths.add(path);
					System.out.println("--------permitAll:" + path);
				}
			}
		}

		protected void configure(HttpSecurity http) throws Exception {
			this.initPermitAll();
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry exp = http
					.antMatcher("/api/**").exceptionHandling()
					// 已登入用户的权限错误
					.accessDeniedHandler(accessDeniedHandler())
					// 未登入用户的权限错误
					.authenticationEntryPoint(accessDeniedHandler())

					.and().cors()
					// 配置访问权限
					.and().authorizeRequests();
			for (String api : permitAllPaths) {
				exp.antMatchers(api).permitAll();
			}
			     exp.antMatchers("/api/install").permitAll()
					.antMatchers("/api/user/smsCode").permitAll()
					.antMatchers("/api/user/forgetPassword").permitAll()
					// 注册接口放行
					.antMatchers("/api/user/register/phone").permitAll()
					.antMatchers("/api/user/register").permitAll()
					// 其余接口都要权限访问
					.anyRequest().authenticated()

					.and().formLogin()
					// .loginProcessingUrl("/api/user/login").permitAll()
					// .loginProcessingUrl("/api/user/loginByPhonePassword").permitAll()
					// .loginProcessingUrl("/api/user/loginByPhoneVerifyCode").permitAll()
					// .successHandler(authenticationHandler())
					// .failureHandler(authenticationHandler())

					.and().logout()
					// 指定URL
					.logoutUrl("/api/user/logout")
					// 登出前调用，可用于日志
					.addLogoutHandler(logoutHandler())
					// 登出后调用，用户信息已不存在
					.logoutSuccessHandler(logoutHandler())
					// 表示是否要在退出登录后让当前session失效，默认为true。
					.invalidateHttpSession(true)
					// 指定退出登录后需要删除的cookie名称，多个cookie之间以逗号分隔。
					// .deleteCookies("JSESSIONID")
					.and().requiresChannel()
					// 会视为请求需要安全通道，并自动把请求重定向到https上
					// .antMatchers("/").requiresSecure()
					// 回视为请求不需要安全http通道
					//.anyRequest().requiresInsecure()

					// 登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
					// .and().rememberMe()
					// 过期时间
					// .tokenValiditySeconds(1209600)
					// 用来标记存放token的cookie
					// .key("token_key")
					.and().httpBasic()
					// 关闭CSRF
					.and().csrf().disable();

			http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			http.addFilterBefore(phoneValidateCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			http.addFilterBefore(weixinAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			// 用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
			http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
			// 在适当的地方加入
			// http.addFilterAt(filterSecurityInterceptor(),
			// FilterSecurityInterceptor.class);

		}

	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().permitAll();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/css/**");
			web.ignoring().antMatchers("/js/**");
			web.ignoring().antMatchers("/fonts/**");
		}

	}

}
