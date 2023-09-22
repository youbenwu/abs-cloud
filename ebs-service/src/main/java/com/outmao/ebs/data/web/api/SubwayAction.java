package com.outmao.ebs.data.web.api;



import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.data.service.SubwayService;
import com.outmao.ebs.data.vo.SubwayVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "data-subway", tags = "数据-地铁数据")
@RestController
@RequestMapping("/api/data/subway")
public class SubwayAction {

	@Autowired
	private SubwayService subwayService;

	@PreAuthorize("permitAll")
	@ApiOperation(value = "获取地铁数据列表", notes = "获取地铁数据列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<SubwayVO> getSubwayVOList(String city)
	{
		if(StringUtil.isNotEmpty(city))
			return subwayService.getSubwayVOListByCity(city);
		return subwayService.getSubwayVOList();
	}




}
