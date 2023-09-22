package com.outmao.ebs.common.services.juhe;



import com.outmao.ebs.common.services.juhe.vo.JuheResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//聚合数据
@FeignClient(url = "${juhe.api}", name = "juhe-api")
public interface JuheClient {

	//发送短信
	//tpl_value:#code#=431515
	@RequestMapping(value="/sms/send?key=${juhe.key}&dtype=json",method= RequestMethod.GET)
	public JuheResult smsSend(
            @RequestParam("mobile") String mobile,
            @RequestParam("tpl_id") String tpl_id,
            @RequestParam("tpl_value") String tpl_value);



}
