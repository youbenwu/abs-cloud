package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;

import java.util.List;

public interface AdvertPvLogDomain {


    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request);


    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId);


}
