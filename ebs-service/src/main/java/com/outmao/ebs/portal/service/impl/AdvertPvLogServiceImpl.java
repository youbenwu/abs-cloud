package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.dto.SaveAdvertPvLogListDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AdvertPvLogServiceImpl extends BaseService implements AdvertPvLogService {

    @Autowired
    private AdvertPvLogDomain advertPvLogDomain;

    @Autowired
    private HotelService hotelService;


    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request) {
        AdvertPvLog log= advertPvLogDomain.saveAdvertPvLog(request);
        return log;
    }


    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogList(SaveAdvertPvLogListDTO request) {
        List<AdvertPvLog> list=new ArrayList<>(request.getAdverts().size());
        request.getAdverts().forEach(t->{
            AdvertPvLog log=new AdvertPvLog();
            log.setAdvertId(t);
            log.setUserId(request.getUserId());
            log.setSpaceId(request.getSpaceId());
            saveAdvertPvLog(log);
            list.add(log);
        });
        return list;
    }

    @Async
    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogListAsync(SaveAdvertPvLogListDTO request) {
        return saveAdvertPvLogList(request);
    }


    @Override
    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId) {
        List<QyStatsAdvertByHotelVO> vos=advertPvLogDomain.getQyStatsAdvertByHotelVOList(advertId);

        List<HotelVO> hotelVOS=hotelService.getHotelVOListByOrgIdIn(vos.stream().map(t->t.getHotelId()).collect(Collectors.toList()));

        Map<Long,HotelVO> hotelVOMap=hotelVOS.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        vos.forEach(t->{
            HotelVO vo=hotelVOMap.get(t.getHotelId());
            if(vo!=null){
                t.setHotelName(vo.getName());
                if(vo.getContact()!=null&&vo.getContact().getAddress()!=null) {
                    t.setCity(vo.getContact().getAddress().getCity());
                }
            }
        });

        return vos;
    }


}
