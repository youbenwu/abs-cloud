package com.outmao.ebs.user.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.security.util.SecurityUtil;
import com.outmao.ebs.security.vo.SecurityUser;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.domain.*;
import com.outmao.ebs.user.dto.*;
import com.outmao.ebs.user.entity.*;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;

@Order(1)
@Service
public class UserServiceImpl extends BaseService implements UserService, CommandLineRunner {

	@Autowired
	private UserDomain userDomain;

	@Autowired
	private UserOauthDomain userOauthDomain;


	@Transactional
	@Override
	public void run(String... args) throws Exception {

		//创建管理员用户
		User admin = getUserByUsername("admin");
		if (admin == null) {
			registerUser(new RegisterDTO(
					Oauth.USERNAME.getName(),
					"admin",
					"123456"
			));
		}

	}


	@Override
	public User registerUser(RegisterDTO request) {
		User user= userDomain.registerUser(request);
		return user;
	}



	@Override
	public User saveUser(UserDTO request) {
		return userDomain.saveUser(request);
	}

	@Override
	public User modifyUser(UserDTO request) {
		return userDomain.modifyUser(request);
	}

	@Override
	public User modifyUserPassword(Long id,String oldPassword, String password) {
		return userDomain.modifyUserPassword(id,oldPassword,password);
	}

	@Override
	public User modifyUserPhone(Long id, String phone) {
		return userDomain.modifyUserPhone(id,phone);
	}


	@Override
	public User setUserVerified(Long id, boolean verified, String realName) {
		return userDomain.setUserVerified(id,verified,realName);
	}

	@Override
	public User setUserEntVerified(Long id,boolean entVerified,Long enterpriseId,String enterpriseName) {
		return userDomain.setUserEntVerified(id,entVerified,enterpriseId,enterpriseName);
	}

	@Override
	public long getUserCount() {
		return userDomain.getUserCount();
	}

	@Override
	public User getUserById(Long id) {
		return userDomain.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDomain.getUserByUsername(username);
	}

	@Override
	public List<SimpleUserVO> getSimpleUserVOListByIdIn(Collection<Long> idIn) {
		return userDomain.getSimpleUserVOListByIdIn(idIn);
	}

	@Override
	public List<ContactUserVO> getContactUserVOListByIdIn(Collection<Long> idIn) {
		return userDomain.getContactUserVOListByIdIn(idIn);
	}

	@Override
	public UserVO getUserVOById(Long id) {
		return userDomain.getUserVOById(id);
	}

	@Override
	public Page<UserVO> getUserVOPage(GetUserListDTO request, Pageable pageable) {
		return userDomain.getUserVOPage(request,pageable);
	}

	@Override
	public Page<UserDetailsVO> getUserDetailsVOPage(GetUserListDTO request, Pageable pageable) {
		return userDomain.getUserDetailsVOPage(request,pageable);
	}

	@Override
	public List<UserDetailsVO> getUserDetailsVOListByIdIn(Collection<Long> idIn) {
		return userDomain.getUserDetailsVOListByIdIn(idIn);
	}

	@Override
	public Page<HuaUserVO> getHuaUserVOPage(GetUserListDTO request, Pageable pageable) {
		return userDomain.getHuaUserVOPage(request,pageable);
	}

	@Override
	public List<QyUserVO> getQyUserVOListByIdIn(Collection<Long> idIn) {
		return userDomain.getQyUserVOListByIdIn(idIn);
	}

	@Override
	public UserDetails saveUserDetails(UserDetailsDTO request) {
		return userDomain.saveUserDetails(request);
	}

	@Override
	public UserDetails modifyUserDetails(UserDetailsDTO request) {
		return userDomain.modifyUserDetails(request);
	}

	@Override
	public UserDetailsVO getUserDetailsVOByUserId(Long userId) {
		return userDomain.getUserDetailsVOByUserId(userId);
	}

	@Override
	public UserOauth registerUserOauth(Long userId, String oauth, String principal, String credentials) {
		return userOauthDomain.registerUserOauth(userId,oauth,principal,credentials);
	}

	@Override
	public UserOauth getUserAuthByPrincipal(String principal) {
		return userOauthDomain.getUserAuthByPrincipal(principal);
	}

	@Override
	public List<UserOauth> getUserOauth(Long userId, Oauth oauth) {
		return userOauthDomain.getUserOauth(userId,oauth);
	}

	@Override
	public String getWeChatOpenId() {
		SecurityUser user= SecurityUtil.currentUser();
		if(user.getSession().getSessionKey()!=null){
			return user.getUsername();
		}
		List<UserOauth> oauths=getUserOauth(user.getId(),Oauth.WECHAT);
		if(oauths.size()>0){
			return oauths.get(0).getPrincipal();
		}
		return null;
	}

	@Override
	public UserOauthSession getUserOauthSessionByToken(String token) {
		return userOauthDomain.getUserOauthSessionByToken(token);
	}

	@Override
	public UserOauthSession setAuthenticated(SetAuthenticatedDTO request) {
		return userOauthDomain.setAuthenticated(request);
	}

	@Override
	public UserOauthSession setAuthenticatedNot(Long sessionId) {
		return userOauthDomain.setAuthenticatedNot(sessionId);
	}

	@Override
	public Page<User> getUserPageByType(int type, Pageable pageable) {
		return userDomain.getUserPageByType(type,pageable);
	}

}
