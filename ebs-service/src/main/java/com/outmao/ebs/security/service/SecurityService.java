package com.outmao.ebs.security.service;



import com.outmao.ebs.common.services.wxmp.WXMPSessionResult;
import com.outmao.ebs.security.vo.SecurityUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import javax.servlet.http.HttpServletRequest;


public interface SecurityService extends UserDetailsService {


	public boolean hasPermission(Long orgId, String url, String permission);

	/*
	 *
	 * 通过token加载用户信息，用于通过token自动登录
	 *
	 * */
	public SecurityUser loadUserByToken(String token);

	/*
	 *
	 * 通过微信授权登录码加载用户信息，用于通过微信第三方登录
	 *
	 * */
	public SecurityUser loadUserOrRegisterByWeChatCode(String code);

	/*
	 *
	 * 微信登录码加载用户信息
	 *
	 * */
	public SecurityUser loadUserOrRegisterByWx(String session_key,String unionid,String openid,String phone,String nickname);

	/*
	 *
	 * 注册手机用户
	 *
	 * */
	public SecurityUser loadUserOrRegisterByPhone(String phone);

	/*
	 *
	 * 设置登录状态,实现方法要生成token
	 *
	 * */
	public SecurityUser setAuthenticated(SecurityUser user, boolean authenticated);


	public void sendSmsCode(String phone, String type, HttpServletRequest request);


	public WXMPSessionResult getWeChatSession(String code);

	public Object getWeChatPhoneNumber(String encryptedData, String iv);

	public Object getWeChatPhoneNumber(String code);


}
