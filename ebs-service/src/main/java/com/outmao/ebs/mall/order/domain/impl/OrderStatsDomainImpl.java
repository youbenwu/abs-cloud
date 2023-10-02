package com.outmao.ebs.mall.order.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.mall.order.domain.OrderStatsDomain;
import com.outmao.ebs.mall.order.entity.QOrder;
import com.outmao.ebs.mall.order.vo.StatsOrderVO;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class OrderStatsDomainImpl extends BaseDomain implements OrderStatsDomain {

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByDays(Date fromTime, Date toTime) {
        QOrder e=QOrder.order;

        List<Tuple> list=QF.select(e.count(),e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).groupBy(e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsOrderVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();
        list.forEach(t->{
            StatsOrderVO vo=new StatsOrderVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month()),t.get(e.createTime.dayOfMonth()));
            vo.setTime(calendar.getTime());
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.totalAmount.sum()));
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<StatsOrderVO> getStatsOrderVOListByUserIdIn(Collection<Long> userIdIn) {
        QOrder e=QOrder.order;

        List<Tuple> list=QF.select(e.user.id,e.count(),e.totalAmount.sum()).groupBy(e.user.id).from(e).where(e.user.id.in(userIdIn)).fetch();

        List<StatsOrderVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsOrderVO vo=new StatsOrderVO();
            vo.setUserId(t.get(e.user.id));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.totalAmount.sum()));
            vos.add(vo);
        });

        return vos;
    }

}
