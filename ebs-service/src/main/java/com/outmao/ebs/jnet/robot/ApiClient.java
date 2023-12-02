package com.outmao.ebs.jnet.robot;


import com.outmao.ebs.common.vo.Result;
import com.outmao.ebs.user.vo.UserDetailsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "http://localhost:8089", name = "api")
public interface ApiClient {

	@RequestMapping(value="/api/user/register",method= RequestMethod.POST)
	public Result<UserDetailsVO> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") int type);

	@RequestMapping(value="/api/user/login",method= RequestMethod.POST)
	public Result<UserDetailsVO> login(@RequestParam("username") String username, @RequestParam("password") String password);





}
