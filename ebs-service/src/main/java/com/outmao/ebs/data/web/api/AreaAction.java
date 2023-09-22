package com.outmao.ebs.data.web.api;



import com.outmao.ebs.data.service.AreaService;
import com.outmao.ebs.data.vo.AreaVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(value = "data-area", tags = "数据-地区数据")
@RestController
@RequestMapping("/api/data/area")
public class AreaAction {

	@Autowired
	private AreaService areaService;

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取（国家、省、市）列表", notes = "获取（国家、省、市）列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<AreaVO> getAreaVOList() {
		List<AreaVO> list = areaService.getAreaVOList();
		return list;
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取国内（省、市，区）列表", notes = "获取国内（省、市，区）列表")
	@RequestMapping(value = "/provinces", method = RequestMethod.GET)
	public List<AreaVO> getProvinceVOList() {
		return areaService.getProvinceVOList();
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取国内市列表", notes = "获取国内市列表")
	@RequestMapping(value = "/citys", method = RequestMethod.GET)
	public List<AreaVO> getCityVOList() {
		return areaService.getCityVOList();
	}

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取子一级地区列表", notes = "获取子一级地区列表列表")
	@RequestMapping(value = "/children", method = RequestMethod.GET)
	public List<AreaVO> getAreaVOChildren(Long parentId) {
		return areaService.getAreaVOChildren(parentId);
	}


}
