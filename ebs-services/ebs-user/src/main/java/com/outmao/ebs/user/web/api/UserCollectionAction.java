package com.outmao.ebs.user.web.api;

import com.outmao.ebs.user.dto.UserCollectionDTO;
import com.outmao.ebs.user.entity.UserCollection;
import com.outmao.ebs.user.service.UserCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "user-collection", tags = "用户-收藏用户")
@RestController
@RequestMapping("/api/user/collection")
public class UserCollectionAction {

	@Autowired
    private UserCollectionService userCollectionService;


	// UserCollection 用户收藏相关接口
	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存收藏用户信息", notes = "保存收藏用户信息")
	@PostMapping("/save")
	public UserCollection saveUserCollection(UserCollectionDTO request) {
		return userCollectionService.saveUserCollection(request);
	}

	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取收藏用户信息", notes = "获取收藏用户信息")
	@PostMapping("/get")
	public UserCollection getUserCollection(Long userId, Long toId) {
		return userCollectionService.getUserCollection(userId,toId);
	}



}
