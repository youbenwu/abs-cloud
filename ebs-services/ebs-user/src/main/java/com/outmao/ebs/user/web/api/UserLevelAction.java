package com.outmao.ebs.user.web.api;


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


@Api(value = "user-level", tags = "用户-等级配置")
@RestController
@RequestMapping("/api/user/level")
public class UserLevelAction {

	@Autowired
    private UserLevelService userLevelService;


	// UserLevel 用户等级划分相关接口
	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取用户等级划分信息", notes = "获取用户等级划分信息")
	@PostMapping("/list")
	public List<UserLevel> getUserLevelList(){
		return userLevelService.getUserLevelList();
	}



}
