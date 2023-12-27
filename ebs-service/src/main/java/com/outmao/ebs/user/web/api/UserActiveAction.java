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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Api(value = "user-active", tags = "用户-用户活跃记录")
@RestController
@RequestMapping("/api/user/active")
public class UserActiveAction {

	@Autowired
    private UserActiveService userActiveService;


	@PreAuthorize("principal.id.equals(#request.userId)")
	@ApiOperation(value = "用户活跃记录", notes = "用户活跃记录")
	@PostMapping("/save")
	public UserActive saveUserActive(UserActiveDTO request){
		return userActiveService.saveUserActive(request);
	}


	@PreAuthorize("permitAll")
	@ApiOperation(value = "活跃用户数量统计按天", notes = "活跃用户数量统计按天")
	@PostMapping("/stats/days")
	public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByDays(Date fromTime, Date toTime) {
		if(true)
		return getFalseData();
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


	private List<StatsUserActiveCountVO> getFalseData(){
		List<StatsUserActiveCountVO> list=new ArrayList<>(7);

        list.add(getFalseData(7,300));
		list.add(getFalseData(6,600));
		list.add(getFalseData(5,300));
		list.add(getFalseData(4,300));
		list.add(getFalseData(3,800));
		list.add(getFalseData(2,300));
		list.add(getFalseData(1,900));
		list.add(getFalseData(0,300));
		return list;
	}

	private StatsUserActiveCountVO getFalseData(int deforeDays,int count){

		Date date1= DateUtil.beforeDays(deforeDays);
		StatsUserActiveCountVO vo1=new StatsUserActiveCountVO();
		vo1.setTime(date1);
		vo1.setIndex(DateUtil.getDateMMdd(date1));
		vo1.setCount(count);
		return vo1;
	}


}
