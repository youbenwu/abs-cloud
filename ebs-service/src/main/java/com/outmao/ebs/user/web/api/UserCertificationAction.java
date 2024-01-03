package com.outmao.ebs.user.web.api;


import com.outmao.ebs.user.dto.UserCertificationDTO;
import com.outmao.ebs.user.service.UserCertificationService;
import com.outmao.ebs.user.vo.UserCertificationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "user-certification", tags = "用户-实名认证")
@RestController
@RequestMapping("/api/user/certification")
public class UserCertificationAction {

	@Autowired
    private UserCertificationService userCertificationService;


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存实名认证资料", notes = "保存实名认证资料")
	@PostMapping("/save")
	public void saveUserCertification(UserCertificationDTO request){
		userCertificationService.saveUserCertification(request);
	}

	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取实名认证资料", notes = "获取实名认证资料")
	@PostMapping("/get")
	public UserCertificationVO getUserCertificationVOByUserId(Long userId){
		return userCertificationService.getUserCertificationVOByUserId(userId);
	}



}
