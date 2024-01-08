package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import com.outmao.ebs.user.dto.GetUserListDTO;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.dto.UserDTO;
import com.outmao.ebs.user.dto.UserDetailsDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import com.outmao.ebs.user.vo.HuaUserVO;
import com.outmao.ebs.user.vo.UserDetailsVO;
import com.outmao.ebs.user.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AccessPermissionGroup(title="用户管理",url="/user",name="",children = {

		@AccessPermissionParent(title = "用户信息管理",url = "/user/info",name = "",children = {
				@AccessPermission(title = "保存用户信息",url = "/user/info",name = "save"),
				@AccessPermission(title = "删除用户信息",url = "/user/info",name = "delete"),
				@AccessPermission(title = "读取用户信息",url = "/user/info",name = "read"),
		}),

		@AccessPermissionParent(title = "用户等级管理",url = "/user/level",name = "",children = {
				@AccessPermission(title = "保存用户等级",url = "/user/level",name = "save"),
				@AccessPermission(title = "删除用户等级",url = "/user/level",name = "delete"),
				@AccessPermission(title = "读取用户等级",url = "/user/level",name = "read"),
		}),

		@AccessPermissionParent(title = "实名认证信息管理",url = "/user/certification",name = "",children = {
				@AccessPermission(title = "保存实名认证信息",url = "/user/certification",name = "save"),
				@AccessPermission(title = "删除实名认证信息",url = "/user/certification",name = "delete"),
				@AccessPermission(title = "读取实名认证信息",url = "/user/certification",name = "read"),
				@AccessPermission(title = "审核实名认证信息",url = "/user/certification",name = "status"),
		}),

		@AccessPermissionParent(title = "用户位置信息管理",url = "/user/location",name = "",children = {
				@AccessPermission(title = "读取用户位置信息",url = "/user/location",name = "read"),
		}),


		@AccessPermissionParent(title = "用户配置数据管理",url = "/user/data",name = "",children = {
				@AccessPermission(title = "保存用户配置数据",url = "/user/data",name = "save"),
				@AccessPermission(title = "删除用户配置数据",url = "/user/data",name = "delete"),
				@AccessPermission(title = "读取用户配置数据",url = "/user/data",name = "read"),
		}),

})
@Api(value = "account-user", tags = "后台-用户")
@RestController
@RequestMapping("/api/admin/user")
public class UserAdminAction {

	@Autowired
    private UserService userService;


	// User

	// 注册用户信息
	@PreAuthorize("hasPermission(null,'/user/info','save')")
	@ApiOperation(value = "注册用户信息", notes = "注册用户信息")
	@PostMapping("/register")
	public void registerUser(@RequestBody RegisterDTO request){
		userService.registerUser(request);
	}



	// 修改用户信息
	@PreAuthorize("hasPermission(null,'/user/info','save')")
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息")
	@PostMapping("/modify")
	public void modifyUser(UserDTO request){
		userService.modifyUser(request);
	}

	// 修改密码
	@PreAuthorize("hasPermission(null,'/user/info','save')")
	@ApiOperation(value = "修改密码", notes = "修改密码")
	@PostMapping("/modifyPassword")
	public void modifyUserPassword(Long id,String oldPassword, String password){
		userService.modifyUserPassword(id,oldPassword,password);
	}

	// 修改手机号
	@PreAuthorize("hasPermission(null,'/user/info','save')")
	@ApiOperation(value = "修改手机号", notes = "修改手机号")
	@PostMapping("/modifyPhone")
	public void modifyUserPhone(Long id, String phone){
		userService.modifyUserPhone(id,phone);
	}

	@ApiOperation(value = "获取用户数量", notes = "获取用户数量")
	@PostMapping("/count")
	public long getUserCount() {
		return userService.getUserCount();
	}

	//获取用户
	@PreAuthorize("hasPermission(null,'/user/info','read')")
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/get")
	public UserVO getUserVOById(Long id){
		return userService.getUserVOById(id);
	}


	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/getByUsername")
	public User getUserByUsername(String username){
		return userService.getUserByUsername(username);
	}

	//获取用户列表
	@PreAuthorize("hasPermission(null,'/user/info','read')")
	@ApiOperation(value = "获取用户", notes = "获取用户")
	@PostMapping("/page")
	public Page<UserVO> getUserVOPage(GetUserListDTO request, Pageable pageable){
		return userService.getUserVOPage(request,pageable);
	}

	@PreAuthorize("hasPermission(null,'/user/info','read')")
	@ApiOperation(value = "获取用户详情列表", notes = "获取用户详情列表")
	@PostMapping("/details/page")
	public Page<UserDetailsVO> getUserDetailsVOPage(GetUserListDTO request, Pageable pageable) {
		return userService.getUserDetailsVOPage(request,pageable);
	}

	@PreAuthorize("hasPermission(null,'/user/info','read')")
	@ApiOperation(value = "获取成都花卉小程序用户列表", notes = "获取成都花卉小程序用户列表")
	@PostMapping("/page2")
	//获取成都花卉小程序用户列表
	public Page<HuaUserVO> getHuaUserVOPage(GetUserListDTO request, Pageable pageable){
		return userService.getHuaUserVOPage(request,pageable);
	}

	//UserDetails
	// 修改用户详细信息
	@PreAuthorize("hasPermission(null,'/user/info','save')")
	@ApiOperation(value = "修改用户详细信息", notes = "修改用户详细信息")
	@PostMapping("/details/modify")
	public void modifyUserDetails(UserDetailsDTO request){
		userService.modifyUserDetails(request);
	}

	// 获取用户详细信息
	@PreAuthorize("hasPermission(null,'/user/info','read')")
	@ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息息")
	@PostMapping("/details/get")
	public UserDetailsVO getUserDetailsVOByUserId(Long userId){
		return userService.getUserDetailsVOByUserId(userId);
	}





}
