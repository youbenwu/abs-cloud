package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertPvForDeviceVO;

import java.util.Date;
import java.util.List;

public interface AdvertPvLogDomain {


    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request);


    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId);


    public List<QyStatsAdvertPvForDeviceVO> getQyStatsAdvertPvForDeviceVOList(Date fromTime,Date toTime);


}
