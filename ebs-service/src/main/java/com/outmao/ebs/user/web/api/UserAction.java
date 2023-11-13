package com.outmao.ebs.user.web.api;



import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.wxmp.WXMPSessionResult;
import com.outmao.ebs.common.util.RequestUtil;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.security.service.SecurityService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.validate.ValidateCodeUtil;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.GetUserListDTO;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.UserDetailsDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.UserDetailsVO;
import com.outmao.ebs.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Api(value = "user", tags = "用户")
@RestController
@RequestMapping("/api/user")
public class UserAction {

	@Autowired
    private UserService userService;

	@Autowired
	private SecurityService securityService;



	@ApiOperation(value = "帐号登录接口", notes = "帐号登录接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "帐号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
	})
	@PostMapping("/login")
	public void loginUser(String username, String password) { }


	@ApiOperation(value = "手机号登录接口", notes = "手机号登录接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String"),
	})
	@PostMapping("/login/phone")
	public void loginUserByPhone(String phone, String verifyCode) { }


	@ApiOperation(value = "微信码登录接口", notes = "微信码登录接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "微信码", required = true, dataType = "String"),
	})
	@PostMapping("/login/weChatCode")
	public void loginUserByWeChatCode(String code) { }


	@ApiOperation(value = "小程序登录", notes = "小程序登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "session_code", value = "小程序登录CODE", required = true, dataType = "String"),
			@ApiImplicitParam(name = "phone_code", value = "小程序获取手机号CODE", required = true, dataType = "String"),
	})
	@PostMapping("/login/wx")
	public void loginUserByWeChat(String session_code,String phone_code) { }

	@ApiOperation(value = "登出接口", notes = "登出接口")
	@ApiImplicitParams({})
	@PostMapping("/logout")
	public void logout() { }


	@ApiOperation(value = "获取手机验证码", notes = "给用户发送验证码短信")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "type", value = "业务类型", required = true, dataType = "String"), })
	@PostMapping("/smsCode")
	public boolean sendSmsCode(String phone, String type, HttpServletRequest request) {
		//System.out.println("session:"+request.getSession().getId());
		securityService.sendSmsCode(phone,type,request);
		return true;
	}

	@ApiOperation(value = "获取微信用户绑定的手机号", notes = "获取微信用户绑定的手机号")
	@PostMapping("/getWeChatPhoneNumber")
	public Object getWeChatPhoneNumber(String encryptedData, String iv) {
		return securityService.getWeChatPhoneNumber(encryptedData,iv);
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "小程序获取手机号", notes = "小程序获取手机号")
	@PostMapping("/wxPhoneNumber")
	public Object getWeChatPhoneNumber(String code) {
		return securityService.getWeChatPhoneNumber(code);
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取小程序登录信息", notes = "获取小程序登录信息")
	@PostMapping("/wxSession")
	public WXMPSessionResult getWeChatSession(String code) {
		return securityService.getWeChatSession(code);
	}

	@ApiOperation(value = "帐号注册接口", notes = "帐号注册接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "帐号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String"),
	})
	@PostMapping("/register")
	public void registerUser(String username, String password,String nickname) {
		Map<String,Object> args= RequestUtil.getHeaders();
		args.put("nickname",nickname);
		userService.registerUser(new RegisterDTO(Oauth.USERNAME.getName(),username,password,0,args));
	}

	@ApiOperation(value = "手机号注册接口", notes = "手机号注册接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
			@ApiImplicitParam(name = "validateCode", value = "验证码", required = true, dataType = "String")
	})
	@PostMapping("/register/phone")
	public void registerUserByPhone(String phone, String password, String validateCode,String nickname, HttpServletRequest request) {
		ValidateCodeUtil.verify(phone,validateCode, request);
		Map<String,Object> args= RequestUtil.getHeaders();
		args.put("nickname",nickname);
		userService.registerUser(new RegisterDTO(Oauth.PHONE.getName(),phone,password,0,args));
	}


	//绑定手机号
	@ApiOperation(value = "绑定手机号", notes = "绑定手机号")
	@ApiImplicitParams({})
	@PreAuthorize("principal.id.equals(#userId)")
	@PostMapping("/modifyPhone")
	public void modifyPhone(Long userId,String phone){
		 userService.modifyUserPhone(userId,phone);
	}


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "修改密码", notes = "修改密码")
	@PostMapping("/modifyPassword")
	public void modifyPassword(Long userId,String oldPassword, String password, String validateCode, HttpServletRequest request) {
		UserDetailsVO details=userService.getUserDetailsVOByUserId(userId);
		if(StringUtil.isEmpty(oldPassword)) {
			ValidateCodeUtil.verify(details.getPhone(),validateCode, request);
		}
		userService.modifyUserPassword(userId,oldPassword, password);
		userService.setAuthenticatedNot(SecurityUtil.currentUser().getSession().getSessionId());
		SecurityUtil.logout();
	}

	
	@PreAuthorize("permitAll")
	@ApiOperation(value = "忘记密码", notes = "忘记密码")
	@PostMapping("/forgetPassword")
	public void forgetPassword(String username, String password, String validateCode, HttpServletRequest request) {
		User user=userService.getUserByUsername(username);
		ValidateCodeUtil.verify(user.getDetails().getPhone(),validateCode, request);
		UserOauth auth=userService.getUserAuthByPrincipal(username);
		if(auth==null) {
			throw new BusinessException("用户'"+username+"'不存在");
		}
		userService.modifyUserPassword(auth.getUser().getId(),null, password);
	}


	// User


	//获取用户
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/get")
	public UserVO getUserVOById(Long id){
		return userService.getUserVOById(id);
	}

	//获取用户列表
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/page")
	public Page<UserVO> getUserVOPage(GetUserListDTO request, Pageable pageable){
		return userService.getUserVOPage(request,pageable);
	}


	//UserDetails
	// 修改用户详细信息
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "修改用户详细信息", notes = "修改用户详细信息")
	@PostMapping("/details/modify")
	public void modifyUserDetails(UserDetailsDTO request){
		userService.modifyUserDetails(request);
	}

	// 获取用户详细信息
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息息")
	@PostMapping("/details/get")
	public UserDetailsVO getUserDetailsVOByUserId(Long userId){
		return userService.getUserDetailsVOByUserId(userId);
	}





}
