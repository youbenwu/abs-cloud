package com.outmao.ebs.user.domain;


import com.outmao.ebs.user.dto.GetUserListDTO;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.UserDTO;
import com.outmao.ebs.user.dto.UserDetailsDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.entity.UserDetails;
import com.outmao.ebs.user.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserDomain {

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

	// 获取用户总数
	public long getUserCount();

	// 获取用户
	public User getUserById(Long id);
	// 获取用户
	public User getUserByUsername(String username);
    //获取用户列表
	public List<SimpleUserVO> getSimpleUserVOListByIdIn(Collection<Long> idIn);
	public List<ContactUserVO> getContactUserVOListByIdIn(Collection<Long> idIn);

	public Page<User> getUserPageByType(int type,Pageable pageable);

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
	public UserDetails getUserDetailsByUserId(Long userId);
	// 获取用户详细信息
	public UserDetailsVO getUserDetailsVOByUserId(Long userId);


}
