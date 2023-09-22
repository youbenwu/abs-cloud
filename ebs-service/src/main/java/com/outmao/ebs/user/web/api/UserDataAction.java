package com.outmao.ebs.user.web.api;


import com.outmao.ebs.user.dto.GetUserDataListDTO;
import com.outmao.ebs.user.dto.UserDataDTO;
import com.outmao.ebs.user.entity.UserData;
import com.outmao.ebs.user.service.UserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "user-data", tags = "用户-用户配置数据")
@RestController
@RequestMapping("/api/user/data")
public class UserDataAction {

	@Autowired
    private UserDataService userDataService;


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存用户配置数据", notes = "保存用户配置数据")
	@PostMapping("/save")
	public UserData saveUserData(UserDataDTO request) {
		return userDataService.saveUserData(request);
	}

	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户配置数据列表", notes = "获取用户配置数据列表")
	@PostMapping("/list")
	public List<UserData> getUserDataList(GetUserDataListDTO request) {
		return userDataService.getUserDataList(request);
	}

	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "获取用户配置数据", notes = "获取用户配置数据")
	@PostMapping("/getByName")
	public UserData getUserData(Long userId, String name) {
		return userDataService.getUserData(userId,name);
	}




}
