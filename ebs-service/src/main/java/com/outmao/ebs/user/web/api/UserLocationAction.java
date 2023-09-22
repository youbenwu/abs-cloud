package com.outmao.ebs.user.web.api;


import com.outmao.ebs.user.dto.GetUserLocationListDTO;
import com.outmao.ebs.user.dto.UserLocationDTO;
import com.outmao.ebs.user.entity.UserLocation;
import com.outmao.ebs.user.service.UserLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "user-location", tags = "用户-位置")
@RestController
@RequestMapping("/api/user/location")
public class UserLocationAction {

	@Autowired
    private UserLocationService userLocationService;




	// UserLocation 用户位置信息相关接口

	/*
	 *
	 * 保存用户位置信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存用户位置信息", notes = "保存用户位置信息")
	@PostMapping("/save")
	public UserLocation saveUserLocation(UserLocationDTO request){
		return userLocationService.saveUserLocation(request);
	}

	/*
	 *
	 * 获取用户最后位置信息
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取用户最后位置信息", notes = "获取用户最后位置信息")
	@PostMapping("/getByUser")
	public UserLocation getUserLocationByUserId(Long userId){
		return userLocationService.getUserLocationByUserId(userId);
	}


	/*
	 *
	 * 获取用户位置分页列表
	 *
	 * */
	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取用户位置分页列表", notes = "获取用户位置分页列表")
	@PostMapping("/page")
	public Page<UserLocation> getUserLocationPage(GetUserLocationListDTO request, Pageable pageable){
		return userLocationService.getUserLocationPage(request,pageable);
	}


}
