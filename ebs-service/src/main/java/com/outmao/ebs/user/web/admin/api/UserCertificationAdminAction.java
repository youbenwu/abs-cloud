package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.user.dto.*;
import com.outmao.ebs.user.service.UserCertificationService;
import com.outmao.ebs.user.vo.UserCertificationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-user-certification", tags = "后台-用户-实名认证")
@RestController
@RequestMapping("/api/admin/user/certification")
public class UserCertificationAdminAction {

	@Autowired
    private UserCertificationService userCertificationService;


	@PreAuthorize("hasPermission(null,'/user/certification','delete')")
	@ApiOperation(value = "删除实名认证资料", notes = "删除实名认证资料")
	@PostMapping("/delete")
	public void deleteUserCertificationById(Long id){
		userCertificationService.deleteUserCertificationById(id);
	}

	@PreAuthorize("hasPermission(null,'/user/certification','status')")
	@ApiOperation(value = "实名认证资料审核", notes = "实名认证资料审核")
	@PostMapping("/setStatus")
	public void setUserCertificationStatus(SetUserCertificationStatusDTO request){
		userCertificationService.setUserCertificationStatus(request);
	}


	@PreAuthorize("hasPermission(null,'/user/certification','read')")
	@ApiOperation(value = "获取实名认证资料列表", notes = "获取实名认证资料列表")
	@PostMapping("/page")
	public Page<UserCertificationVO> getUserCertificationVOPage(GetUserCertificationListDTO request, Pageable pageable){
		return userCertificationService.getUserCertificationVOPage(request,pageable);
	}



}
