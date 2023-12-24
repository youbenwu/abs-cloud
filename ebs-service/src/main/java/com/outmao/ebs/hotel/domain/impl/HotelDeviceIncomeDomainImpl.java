package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.dao.HotelDeviceIncomeDao;
import com.outmao.ebs.hotel.dao.HotelDeviceIncomeStatsDao;
import com.outmao.ebs.hotel.domain.HotelDeviceIncomeDomain;
import com.outmao.ebs.hotel.entity.QHotelDeviceIncome;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeStatsVO;
import com.outmao.ebs.hotel.vo.RenterHotelDeviceIncomeTypeStatsVO;
import com.outmao.ebs.hotel.vo.RenterTotalHotelDeviceIncomeStatsVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HotelDeviceIncomeDomainImpl extends BaseDomain implements HotelDeviceIncomeDomain {


    @Autowired
    private HotelDeviceIncomeDao hotelDeviceIncomeDao;

    @Autowired
    private HotelDeviceIncomeStatsDao hotelDeviceIncomeStatsDao;







    @Override
    public RenterHotelDeviceIncomeStatsVO getRenterHotelDeviceIncomeStatsVO(Long renterId,Long deviceId) {

        RenterHotelDeviceIncomeStatsVO vo=new RenterHotelDeviceIncomeStatsVO();
        vo.setDeviceId(deviceId);

        QHotelDeviceIncome e=QHotelDeviceIncome.hotelDeviceIncome;

        Predicate p1=e.deviceId.eq(deviceId).and(e.renterId.eq(renterId));

        Double total=QF.select(e.renterFee.sum()).from(e).where(p1).fetchOne();

        vo.setAmount(total);

        Date now=new Date();
        Date day7= DateUtil.addDays(now,-7);

        Predicate p2=e.createTime.between(day7,now).and(p1);

        Double amount7day=QF.select(e.renterFee.sum()).from(e).where(p2).fetchOne();

        vo.setAmount7day(amount7day);


        Predicate p3=e.createTime.month().eq(now.getMonth()).and(p1);

        Double amountMonth=QF.select(e.renterFee.sum()).from(e).where(p3).fetchOne();

        vo.setAmountMonth(amountMonth);

        return vo;

    }

    @Override
    public RenterTotalHotelDeviceIncomeStatsVO getRenterTotalHotelDeviceIncomeStatsVO(Long renterId) {

        RenterTotalHotelDeviceIncomeStatsVO vo=new RenterTotalHotelDeviceIncomeStatsVO();


        QHotelDeviceIncome e=QHotelDeviceIncome.hotelDeviceIncome;

        Predicate p1=e.renterId.eq(renterId);

        Double total=QF.select(e.renterFee.sum()).from(e).where(p1).fetchOne();

        vo.setAmount(total);

        Date now=new Date();
        Date day7= DateUtil.addDays(now,-7);

        Predicate p2=e.createTime.between(day7,now).and(p1);

        Double amount7day=QF.select(e.renterFee.sum()).from(e).where(p2).fetchOne();

        vo.setAmount7day(amount7day);


        Predicate p3=e.createTime.month().eq(now.getMonth()).and(p1);

        Double amountMonth=QF.select(e.renterFee.sum()).from(e).where(p3).fetchOne();

        vo.setAmountMonth(amountMonth);

        return vo;
    }


    @Override
    public List<RenterHotelDeviceIncomeTypeStatsVO> getRenterHotelDeviceIncomeTypeStatsVOList(Long renterId) {

        HotelDeviceIncomeType[] types=HotelDeviceIncomeType.values();

        List<RenterHotelDeviceIncomeTypeStatsVO> list=new ArrayList<>(types.length);

        for (HotelDeviceIncomeType type:types){
            RenterHotelDeviceIncomeTypeStatsVO vo=new RenterHotelDeviceIncomeTypeStatsVO();
            vo.setType(type.getType());
            vo.setTypeName(type.getDescription());
            list.add(vo);
        }

        Map<Integer,RenterHotelDeviceIncomeTypeStatsVO> listMap=list.stream().collect(Collectors.toMap(t->t.getType(),t->t));

        QHotelDeviceIncome e=QHotelDeviceIncome.hotelDeviceIncome;

        Predicate p=e.renterId.eq(renterId).and(e.type.in(listMap.keySet()));

        List<Tuple> tuples=QF.select(e.renterFee.sum(),e.type).from(e).where(p).groupBy(e.type).fetch();

        for(Tuple t:tuples){
            RenterHotelDeviceIncomeTypeStatsVO vo=listMap.get(t.get(e.type));
            vo.setAmount(t.get(e.renterFee.sum()));
        }

        return list;
    }


}
