package com.outmao.ebs.user.web.api;


import com.outmao.ebs.user.entity.Online;
import com.outmao.ebs.user.service.OnlineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "user-online", tags = "用户-维持心跳")
@RestController
@RequestMapping("/api/user/online")
public class OnlineAction {

	@Autowired
    private OnlineService onlineService;


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "用户维持心跳", notes = "用户维持心跳")
	@PostMapping("/get")
	public Online getOnline(Long userId,Long time){
		Online online=onlineService.getOnlineByUserId(userId);
		if(online!=null&&online.getTime()==time){
			return null;
		}
		return online;
	}


}
