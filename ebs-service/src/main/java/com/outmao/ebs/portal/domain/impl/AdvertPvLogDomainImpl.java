package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.AdvertPvLogDao;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.dto.StatsAdvertPvDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.entity.QAdvertPvLog;
import com.outmao.ebs.portal.vo.StatsAdvertPvVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class AdvertPvLogDomainImpl extends BaseDomain implements AdvertPvLogDomain {


    @Autowired
    private AdvertPvLogDao advertPvLogDao;


    @Transactional
    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request) {

        request.setCreateTime(new Date());

        AdvertPvLog log=advertPvLogDao.save(request);

        return log;
    }


    @Override
    public StatsAdvertPvVO getStatsAdvertPvVO(StatsAdvertPvDTO request) {
        QAdvertPvLog e=QAdvertPvLog.advertPvLog;
        Predicate p=e.userId.in(request.getUsers())
                .and(e.createTime.between(request.getFromTime(),request.getToTime()));
        Tuple t =QF.select(e.count(),e.pvPrice.sum()).from(e).where(p).fetchOne();
        StatsAdvertPvVO vo=new StatsAdvertPvVO();
        if(t!=null) {
            Double a=t.get(e.pvPrice.sum());
            Long c=t.get(e.count());
            vo.setPvAmount(a==null?0:a);
            vo.setPvCount(c==null?0:c);
        }
        return vo;
    }



}
