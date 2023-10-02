package com.outmao.ebs.mall.order.domain;

import com.outmao.ebs.mall.order.vo.StatsOrderVO;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface OrderStatsDomain {

    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime);


    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn);


}
