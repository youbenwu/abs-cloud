package com.outmao.ebs.common.services.jisu;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//极速数据
@FeignClient(url = "${jisu.api}", name = "jisuapi")
public interface JisuClient {

	@RequestMapping(value="/area/province?appkey=${jisu.appkey}",method= RequestMethod.GET)
	public String getAreaProvinceList();
	
	@RequestMapping(value="/area/city?appkey=${jisu.appkey}",method= RequestMethod.GET)
	public String getAreaCityList(@RequestParam("parentid") String parentid);

	@RequestMapping(value="/area/town?appkey=${jisu.appkey}",method= RequestMethod.GET)
	public String getAreaTownList(@RequestParam("parentid") String parentid);

	
}
