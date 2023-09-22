package com.outmao.ebs.common.services.amap;


import com.outmao.ebs.common.services.amap.vo.GeocodeResult;
import com.outmao.ebs.common.services.amap.vo.RegeocodeResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//极速数据
@FeignClient(url = "http://restapi.amap.com", name = "amap")
public interface AmapClient {

	//地理编码
	@RequestMapping(value="/v3/geocode/geo?key=${amap.key}",method= RequestMethod.GET)
	public GeocodeResult geocode(@RequestParam("address") String address);



	//地理编码 经度在前，纬度在后，经纬度间以“,”分割
	@RequestMapping(value="/v3/geocode/regeo?key=${amap.key}",method= RequestMethod.GET)
	public RegeocodeResult regeocode(@RequestParam("location") String location);




	
}
