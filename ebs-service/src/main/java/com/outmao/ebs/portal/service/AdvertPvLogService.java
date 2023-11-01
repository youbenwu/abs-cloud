package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.StatsAdvertPvDTO;
import com.outmao.ebs.portal.dto.SaveAdvertPvLogListDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.StatsAdvertPvVO;

import java.util.List;

public interface AdvertPvLogService {

    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request);

    public List<AdvertPvLog> saveAdvertPvLogList(SaveAdvertPvLogListDTO request);

    public List<AdvertPvLog> saveAdvertPvLogListAsync(SaveAdvertPvLogListDTO request);

    public StatsAdvertPvVO getStatsAdvertPvVO(StatsAdvertPvDTO request);


}
