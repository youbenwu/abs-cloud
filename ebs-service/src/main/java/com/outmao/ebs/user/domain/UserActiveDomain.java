package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.dto.UserActiveDTO;
import com.outmao.ebs.user.entity.UserActive;
import com.outmao.ebs.user.vo.StatsUserActiveCountVO;

import java.util.Date;
import java.util.List;

public interface UserActiveDomain {


    public UserActive saveUserActive(UserActiveDTO request);

    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByDays(Date fromTime, Date toTime);

    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByMonths(Date fromTime, Date toTime);



}
