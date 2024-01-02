package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogListDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;

import java.util.List;

public interface AdvertPvLogService {

    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request);

    public List<AdvertPvLog> saveAdvertPvLogList(AdvertPvLogListDTO request);

    public List<AdvertPvLog> saveAdvertPvLogListAsync(AdvertPvLogListDTO request);

    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId);


}
