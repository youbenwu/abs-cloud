package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.dao.AdvertPvLogDao;
import com.outmao.ebs.portal.dao.AdvertUvLogDao;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.entity.AdvertUvLog;
import com.outmao.ebs.portal.entity.QAdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AdvertPvLogDomainImpl extends BaseDomain implements AdvertPvLogDomain {


    @Autowired
    private AdvertPvLogDao advertPvLogDao;

    @Autowired
    private AdvertUvLogDao advertUvLogDao;

    @Autowired
    private AdvertDao advertDao;


    @Transactional
    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request) {

        request.setCreateTime(new Date());
        request.setDate(DateUtil.yyyy_MM_dd.format(request.getCreateTime()));

        advertDao.pv(request.getAdvertId());

        String uvKey=request.getUserId()+"_"+request.getAdvertId()+"_"+request.getDate();
        if(advertUvLogDao.findByKey(uvKey)==null){

            advertDao.uv(request.getAdvertId());

            AdvertUvLog uvLog=new AdvertUvLog();
            uvLog.setKey(uvKey);
            uvLog.setUserId(request.getUserId());
            uvLog.setAdvertId(request.getAdvertId());
            uvLog.setCreateTime(request.getCreateTime());
            uvLog.setDate(request.getDate());

            advertUvLogDao.save(uvLog);

        }

        AdvertPvLog log=advertPvLogDao.save(request);

        return log;
    }


    @Override
    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId) {
        QAdvertPvLog e=QAdvertPvLog.advertPvLog;

        List<Tuple> tuples=QF.select(e.count(),e.spaceId).from(e).groupBy(e.spaceId).where(e.advertId.eq(advertId)).fetch();

        List<QyStatsAdvertByHotelVO> list=new ArrayList<>(tuples.size());

        tuples.forEach(t->{
            QyStatsAdvertByHotelVO vo=new QyStatsAdvertByHotelVO();
            vo.setPv(t.get(e.count()));
            vo.setHotelId(t.get(e.spaceId));
            list.add(vo);
        });

        return list;
    }


}
