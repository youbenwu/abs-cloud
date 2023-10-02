package com.outmao.ebs.data.web.admin.api;


import com.outmao.ebs.data.dto.AreaDTO;
import com.outmao.ebs.data.dto.GetAreaListDTO;
import com.outmao.ebs.data.service.AreaService;
import com.outmao.ebs.data.vo.AreaVO;
import com.outmao.ebs.org.common.annotation.AccessPermission;
import com.outmao.ebs.org.common.annotation.AccessPermissionGroup;
import com.outmao.ebs.org.common.annotation.AccessPermissionParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AccessPermissionGroup(title="数据",url="/data",name="",children = {
		@AccessPermissionParent(title = "地区数据管理",url = "/data/area",name = "",children = {
				@AccessPermission(title = "保存地区数据",url = "/data/area",name = "save"),
				@AccessPermission(title = "删除地区数据",url = "/data/area",name = "delete"),
				@AccessPermission(title = "读取地区数据",url = "/data/area",name = "read"),
		}),
})


@Api(value = "account-data-area", tags = "后台-数据-地区数据")
@RestController
@RequestMapping("/api/admin/data/area")
public class AreaAdminAction {

	@Autowired
	private AreaService areaService;

	@PreAuthorize("hasPermission('/data/area','save')")
	@ApiOperation(value = "保存地区数据", notes = "保存地区数据")
	@PostMapping("/save")
	public void saveArea(AreaDTO request){
		areaService.saveArea(request);
	}

	@PreAuthorize("hasPermission('/data/area','delete')")
	@ApiOperation(value = "删除地区数据", notes = "删除地区数据")
	@PostMapping("/delete")
	public void deleteAreaById(Long id) {
		areaService.deleteAreaById(id);
	}

	@PreAuthorize("hasPermission('/data/area','read')")
	@ApiOperation(value = "获取地区数据列表", notes = "获取地区数据列表")
	@PostMapping("/page")
	public Page<AreaVO> getAreaVOPage(GetAreaListDTO request, Pageable pageable){
		return areaService.getAreaVOPage(request,pageable);
	}


}
