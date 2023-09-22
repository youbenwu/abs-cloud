package com.outmao.ebs.user.web.api;


import com.outmao.ebs.user.dto.IdCardDTO;
import com.outmao.ebs.user.service.IdCardService;
import com.outmao.ebs.user.vo.IdCardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "user-idCard", tags = "用户-实名认证")
@RestController
@RequestMapping("/api/user/idCard")
public class IdCardAction {

	@Autowired
    private IdCardService idCardService;


	// IdCard 用户身份证认证资料相关接口

	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "保存实名认证资料", notes = "保存实名认证资料")
	@PostMapping("/save")
	public void saveIdCard(IdCardDTO request) {
		idCardService.saveIdCard(request);
	}


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "获取实名认证资料", notes = "获取实名认证资料")
	@PostMapping("/getByUser")
	public IdCardVO getIdCardVOByUserId(Long userId){
		return idCardService.getIdCardVOByUserId(userId);
	}



}
