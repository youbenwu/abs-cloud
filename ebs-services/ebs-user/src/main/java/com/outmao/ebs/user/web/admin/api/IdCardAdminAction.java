package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.user.dto.GetIdCardListDTO;
import com.outmao.ebs.user.dto.IdCardDTO;
import com.outmao.ebs.user.dto.SetIdCardStatusDTO;
import com.outmao.ebs.user.service.IdCardService;
import com.outmao.ebs.user.vo.IdCardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "account-user-idCard", tags = "后台-用户-实名认证")
@RestController
@RequestMapping("/api/admin/user/idCard")
public class IdCardAdminAction {

	@Autowired
    private IdCardService idCardService;


	// IdCard 用户身份证认证资料相关接口
	@PreAuthorize("hasPermission('/user/IdCard','save')")
	@ApiOperation(value = "保存实名认证资料", notes = "保存实名认证资料")
	@PostMapping("/save")
	public void saveIdCard(IdCardDTO request){
		idCardService.saveIdCard(request);
	}

	@PreAuthorize("hasPermission('/user/IdCard','status')")
	@ApiOperation(value = "审核实名认证资料", notes = "审核实名认证资料")
	@PostMapping("/setStatus")
	public void setIdCardStatus(SetIdCardStatusDTO request) {
		idCardService.setIdCardStatus(request);
	}

	@PreAuthorize("hasPermission('/user/IdCard','read')")
	@ApiOperation(value = "获取实名认证资料", notes = "获取实名认证资料")
	@PostMapping("/getByUser")
	public IdCardVO getIdCardVOByUserId(Long userId){
		return idCardService.getIdCardVOByUserId(userId);
	}

	@PreAuthorize("hasPermission('/user/IdCard','read')")
	@ApiOperation(value = "获取实名认证资料列表", notes = "获取实名认证资料列表")
	@PostMapping("/page")
	public Page<IdCardVO> getIdCardVOPage(@RequestBody GetIdCardListDTO request, Pageable pageable){
		return idCardService.getIdCardVOPage(request,pageable);
	}



}
