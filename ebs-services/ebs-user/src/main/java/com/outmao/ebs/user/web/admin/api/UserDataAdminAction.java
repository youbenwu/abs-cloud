package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.user.dto.GetUserDataListDTO;
import com.outmao.ebs.user.dto.UserDataDTO;
import com.outmao.ebs.user.entity.UserData;
import com.outmao.ebs.user.service.UserDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-user-data", tags = "后台-用户-用户配置数据")
@RestController
@RequestMapping("/api/admin/user/data")
public class UserDataAdminAction {

	@Autowired
    private UserDataService userDataService;


	@PreAuthorize("hasPermission('/user/data','save')")
	@ApiOperation(value = "保存用户配置数据", notes = "保存用户配置数据")
	@PostMapping("/save")
	public UserData saveUserData(UserDataDTO request) {
		return userDataService.saveUserData(request);
	}

	@PreAuthorize("hasPermission('/user/data','read')")
	@ApiOperation(value = "获取用户配置数据列表", notes = "获取用户配置数据列表")
	@PostMapping("/page")
	public Page<UserData> getUserDataPage(GetUserDataListDTO request, Pageable pageable){
		return userDataService.getUserDataPage(request,pageable);
	}




}
