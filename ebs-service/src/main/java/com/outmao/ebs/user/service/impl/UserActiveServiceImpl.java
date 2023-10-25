package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserActiveDomain;
import com.outmao.ebs.user.dto.UserActiveDTO;
import com.outmao.ebs.user.entity.UserActive;
import com.outmao.ebs.user.service.UserActiveService;
import com.outmao.ebs.user.vo.StatsUserActiveCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserActiveServiceImpl extends BaseService implements UserActiveService {

    @Autowired
    private UserActiveDomain userActiveDomain;

    @Override
    public UserActive saveUserActive(UserActiveDTO request) {
        return userActiveDomain.saveUserActive(request);
    }


    @Override
    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByDays(Date fromTime, Date toTime) {
        return userActiveDomain.getStatsUserActiveCountVOListByDays(fromTime,toTime);
    }

    @Override
    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByMonths(Date fromTime, Date toTime) {
        return userActiveDomain.getStatsUserActiveCountVOListByMonths(fromTime,toTime);
    }


}
