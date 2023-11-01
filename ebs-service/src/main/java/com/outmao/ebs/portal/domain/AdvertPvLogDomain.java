package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.StatsAdvertPvDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.StatsAdvertPvVO;


public interface AdvertPvLogDomain {

    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request);


    public StatsAdvertPvVO getStatsAdvertPvVO(StatsAdvertPvDTO request);


}
