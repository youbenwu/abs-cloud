package com.outmao.ebs.user.web.admin.api;


import com.outmao.ebs.user.dto.GetUserAddressListDTO;
import com.outmao.ebs.user.dto.UserAddressDTO;
import com.outmao.ebs.user.entity.UserAddress;
import com.outmao.ebs.user.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "admin-user-address", tags = "后台-用户-地址")
@RestController
@RequestMapping("/api/admin/user/address")
public class UserAddressAdminAction {

	@Autowired
    private UserAddressService userAddressService;


	@PreAuthorize("hasPermission('/user/address','save')")
	@ApiOperation(value = "保存用户地址数据", notes = "保存用户地址数据")
	@PostMapping("/save")
	public UserAddress saveUserAddres(UserAddressDTO request) {
		return userAddressService.saveUserAddres(request);
	}

	@PreAuthorize("hasPermission('/user/address','delete')")
	@ApiOperation(value = "删除用户地址数据", notes = "删除用户地址数据")
	@PostMapping("/delete")
	public void deleteUserAddressById(Long id) {
		userAddressService.deleteUserAddressById(id);
	}

	@PreAuthorize("hasPermission('/user/address','read')")
	@ApiOperation(value = "获取用户地址数据", notes = "获取用户地址数据")
	@PostMapping("/get")
	public UserAddress getUserAddressById(Long id) {
		return userAddressService.getUserAddressById(id);
	}

	@PreAuthorize("hasPermission('/user/address','read')")
	@ApiOperation(value = "获取用户地址数据", notes = "获取用户地址数据")
	@PostMapping("/page")
	public Page<UserAddress> getUserAddressPage(GetUserAddressListDTO request, Pageable pageable) {
		return userAddressService.getUserAddressPage(request,pageable);
	}




}
