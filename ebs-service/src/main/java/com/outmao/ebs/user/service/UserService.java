package com.outmao.ebs.user.service;


import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.*;
import com.outmao.ebs.user.entity.*;
import com.outmao.ebs.user.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {


	// 注册用户信息
	public User registerUser(RegisterDTO request);
	// 修改用户信息
	public User saveUser(UserDTO request);
	// 修改用户信息
	public User modifyUser(UserDTO request);
	// 修改密码
	public User modifyUserPassword(Long id, String oldPassword, String password);
	// 修改手机号
	public User modifyUserPhone(Long id, String phone);

	// 设置用户认证状态
	public User setUserVerified(Long id,boolean verified,String realName);

	// 设置企业认证状态
	public User setUserEntVerified(Long id,boolean entVerified,Long enterpriseId,String enterpriseName);

	// 获取用户总数
	public long getUserCount();

	// 获取用户
	public User getUserById(Long id);
	// 获取用户
	public User getUserByUsername(String username);
	//获取用户列表
	public List<SimpleUserVO> getSimpleUserVOListByIdIn(Collection<Long> idIn);
	public List<ContactUserVO> getContactUserVOListByIdIn(Collection<Long> idIn);

	//获取用户
	public UserVO getUserVOById(Long id);
	//获取用户列表
	public Page<UserVO> getUserVOPage(GetUserListDTO request, Pageable pageable);

	//获取用户列表
	public Page<UserDetailsVO> getUserDetailsVOPage(GetUserListDTO request, Pageable pageable);

	public List<UserDetailsVO> getUserDetailsVOListByIdIn(Collection<Long> idIn);

	//获取成都花卉小程序用户列表
	public Page<HuaUserVO> getHuaUserVOPage(GetUserListDTO request, Pageable pageable);

	//UserDetails
	// 修改用户详细信息
	public UserDetails saveUserDetails(UserDetailsDTO request);
	// 修改用户详细信息
	public UserDetails modifyUserDetails(UserDetailsDTO request);
	// 获取用户详细信息
	public UserDetailsVO getUserDetailsVOByUserId(Long userId);



	// UserOauth
	public UserOauth registerUserOauth(Long userId, String oauth, String principal, String credentials);
	public UserOauth getUserAuthByPrincipal(String principal);
	public List<UserOauth> getUserOauth(Long userId, Oauth oauth);

	/**
	 *
	 * 获取用户微信OpenId
	 *
	 **/
	public String getWeChatOpenId();

	/**
	 *
	 * 获取授权状态
	 *
	 *
	 **/
	public UserOauthSession getUserOauthSessionByToken(String token);
	/**
	 *
	 * 设置授权成功状态
	 *
	 *
	 **/
	public UserOauthSession setAuthenticated(SetAuthenticatedDTO request);

	/**
	 *
	 * 设置非授权状态
	 *
	 *
	 **/
	public UserOauthSession setAuthenticatedNot(Long sessionId);


	public Page<User> getUserPageByType(int type, Pageable pageable);



}
