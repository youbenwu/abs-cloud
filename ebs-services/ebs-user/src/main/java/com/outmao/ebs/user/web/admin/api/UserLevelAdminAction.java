package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.user.dto.UserLevelDTO;
import com.outmao.ebs.user.entity.UserLevel;
import com.outmao.ebs.user.service.UserLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "admin-user-level", tags = "后台-用户-等级配置")
@RestController
@RequestMapping("/api/admin/user/level")
public class UserLevelAdminAction {

	@Autowired
    private UserLevelService userLevelService;


	//UserLevel 用户等级划分相关接口

	@PreAuthorize("hasPermission('/user/level','save')")
	@ApiOperation(value = "保存用户等级划分信息", notes = "保存用户等级划分信息")
	@PostMapping("/save")
	public UserLevel saveUserLevel(UserLevelDTO request){
		return userLevelService.saveUserLevel(request);
	}

	@PreAuthorize("hasPermission('/user/level','read')")
	@ApiOperation(value = "获取用户等级划分信息", notes = "获取用户等级划分信息")
	@PostMapping("/list")
	public List<UserLevel> getUserLevelList(){
		return userLevelService.getUserLevelList();
	}



}
