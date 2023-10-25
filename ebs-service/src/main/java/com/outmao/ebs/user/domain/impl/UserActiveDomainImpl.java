package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.hotel.vo.StatsHotelCountVO;
import com.outmao.ebs.user.dao.UserActiveDao;
import com.outmao.ebs.user.domain.UserActiveDomain;
import com.outmao.ebs.user.dto.UserActiveDTO;
import com.outmao.ebs.user.entity.QUserActive;
import com.outmao.ebs.user.entity.UserActive;
import com.outmao.ebs.user.vo.StatsUserActiveCountVO;
import com.querydsl.core.Tuple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class UserActiveDomainImpl extends BaseDomain implements UserActiveDomain {


    @Autowired
    private UserActiveDao userActiveDao;


    @Transactional
    @Override
    public UserActive saveUserActive(UserActiveDTO request) {
        UserActive a=new UserActive();
        BeanUtils.copyProperties(request,a);
        userActiveDao.save(a);
        return a;
    }


    @Override
    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByDays(Date fromTime, Date toTime) {
        QUserActive e=QUserActive.userActive;
        List<Tuple> list=QF.select(e.userId.countDistinct(),e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).groupBy(e.createTime.year(),e.createTime.month(),e.createTime.dayOfMonth()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsUserActiveCountVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");

        list.forEach(t->{
            StatsUserActiveCountVO vo=new StatsUserActiveCountVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month())-1,t.get(e.createTime.dayOfMonth()));
            vo.setTime(calendar.getTime());
            vo.setIndex(formatter.format(calendar.getTime()));
            vo.setCount(t.get(e.count()));
            vos.add(vo);
        });
        return vos;
    }



    @Override
    public List<StatsUserActiveCountVO> getStatsUserActiveCountVOListByMonths(Date fromTime, Date toTime) {
        QUserActive e=QUserActive.userActive;
        List<Tuple> list=QF.select(e.userId.countDistinct(),e.createTime.year(),e.createTime.month()).groupBy(e.createTime.year(),e.createTime.month()).from(e).where(e.createTime.between(fromTime,toTime)).fetch();

        List<StatsUserActiveCountVO> vos=new ArrayList<>(list.size());

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

        list.forEach(t->{
            StatsUserActiveCountVO vo=new StatsUserActiveCountVO();
            calendar.set(t.get(e.createTime.year()),t.get(e.createTime.month())-1,0);
            vo.setTime(calendar.getTime());
            vo.setIndex(formatter.format(calendar.getTime()));
            vo.setCount(t.get(e.userId.countDistinct()));
            vos.add(vo);
        });
        return vos;
    }



}
