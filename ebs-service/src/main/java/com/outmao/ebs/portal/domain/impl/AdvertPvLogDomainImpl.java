package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.portal.dao.AdvertDao;
import com.outmao.ebs.portal.dao.AdvertPvLogDao;
import com.outmao.ebs.portal.dao.AdvertUvLogDao;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.entity.AdvertUvLog;
import com.outmao.ebs.portal.entity.QAdvertPvLog;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.querydsl.core.Tuple;
import org.springframework.beans.BeanUtils;
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
    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request) {

        AdvertPvLog log=new AdvertPvLog();
        log.setCreateTime(new Date());
        BeanUtils.copyProperties(request,log);
        log.setDate(DateUtil.yyyy_MM_dd.format(log.getCreateTime()));

        String uvKey=log.getUserId()+"_"+log.getAdvertId()+"_"+log.getDate();
        if(advertUvLogDao.findByKey(uvKey)==null){

            advertDao.uv(log.getAdvertId());

            AdvertUvLog uvLog=new AdvertUvLog();
            uvLog.setKey(uvKey);
            uvLog.setUserId(log.getUserId());
            uvLog.setAdvertId(log.getAdvertId());
            uvLog.setCreateTime(log.getCreateTime());
            uvLog.setDate(log.getDate());

            advertUvLogDao.save(uvLog);

        }

        advertPvLogDao.save(log);

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
