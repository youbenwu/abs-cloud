package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogListDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogRequest;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertPvForDeviceVO;

import java.util.Date;
import java.util.List;

public interface AdvertPvLogService {

    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request);

    public AdvertPvLog saveAdvertPvLog(AdvertPvLogRequest request);

    public List<AdvertPvLog> saveAdvertPvLogList(List<AdvertPvLogDTO> request);

    public List<AdvertPvLog> saveAdvertPvLogListAsync(List<AdvertPvLogDTO> request);

    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId);

    public List<QyStatsAdvertPvForDeviceVO> getQyStatsAdvertPvForDeviceVOList(Date fromTime, Date toTime);



}
