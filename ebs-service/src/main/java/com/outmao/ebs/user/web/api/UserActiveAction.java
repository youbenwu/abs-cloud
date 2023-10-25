package com.outmao.ebs.user.web.api;


import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.user.dto.UserActiveDTO;
import com.outmao.ebs.user.entity.UserActive;
import com.outmao.ebs.user.service.UserActiveService;
import com.outmao.ebs.user.vo.StatsUserActiveCountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@Api(value = "user-active", tags = "用户-用户活跃记录")
@RestController
@RequestMapping("/api/user/active")
public class UserActiveAction {

	@Autowired
    private UserActiveService userActiveService;


	@PreAuthorize("principal.id.equals(#userId)")
	@ApiOperation(value = "用户活跃记录", notes = "用户活跃记录")
	@PostMapping("/save")
	public UserActive saveUserActive(UserActiveDTO request){
		return userActiveService.saveUserActive(request);
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "活跃用户数量统计按天", notes = "活跃用户数量统计按天")
	@PostMapping("/stats/days")
	public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByDays(Date fromTime, Date toTime) {
		if(fromTime==null){
			fromTime= DateUtil.beforeDays(7);
		}
		if(toTime==null){
			toTime=new Date();
		}
		return userActiveService.getStatsUserActiveCountVOListByDays(fromTime,toTime);
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "活跃用户数量统计按月", notes = "活跃用户数量统计按月")
	@PostMapping("/stats/months")
	public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByMonths(Date fromTime, Date toTime) {
		if(fromTime==null){
			fromTime= DateUtil.beforeMonths(12);
		}
		if(toTime==null){
			toTime=new Date();
		}
		return userActiveService.getStatsUserActiveCountVOListByMonths(fromTime,toTime);
	}




}
