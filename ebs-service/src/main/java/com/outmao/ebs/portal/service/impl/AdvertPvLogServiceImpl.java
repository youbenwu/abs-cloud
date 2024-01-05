package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.common.constant.HotelDeviceIncomeType;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.service.HotelService;
import com.outmao.ebs.hotel.vo.HotelVO;
import com.outmao.ebs.hotel.vo.SimpleHotelDeviceVO;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.dto.AdvertPvDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogListDTO;
import com.outmao.ebs.portal.dto.AdvertPvLogRequest;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import com.outmao.ebs.portal.vo.AdvertForPvLogVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertPvForDeviceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AdvertPvLogServiceImpl extends BaseService implements AdvertPvLogService {

    @Autowired
    private AdvertPvLogDomain advertPvLogDomain;

    @Autowired
    private AdvertDomain advertDomain;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelDeviceService hotelDeviceService;

    @Transactional
    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLogDTO request) {
        AdvertPvLog log= advertPvLogDomain.saveAdvertPvLog(request);
        advertDomain.pv(new AdvertPvDTO(request.getAdvertId(),request.getAdvertType()));
        return log;
    }

    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLogRequest request) {
        AdvertPvLogDTO dto=new AdvertPvLogDTO();
        BeanUtils.copyProperties(request,dto);

        AdvertForPvLogVO advert=advertDomain.getAdvertForPvLogVOById(dto.getAdvertId());
        dto.setAdvertType(advert.getType());
        dto.setAmount(advert.getBuyPrice()!=null?advert.getBuyPrice():0f);

        SimpleHotelDeviceVO device=hotelDeviceService.getSimpleHotelDeviceVOByUserId(dto.getUserId());

        if(device!=null){
            dto.setSpaceId(device.getHotelId());
        }

        if(dto.getType()==AdvertPvLog.TYPE_SHOW||dto.getType()==AdvertPvLog.TYPE_PV_VIDEO){
            dto.setIncomeType(HotelDeviceIncomeType.AdvertCPM.getType());
        }else{
            dto.setIncomeType(HotelDeviceIncomeType.AdvertCPS.getType());
        }

        return saveAdvertPvLog(dto);
    }

    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogList(List<AdvertPvLogDTO> request) {
        List<AdvertPvLog> list=new ArrayList<>(request.size());
        request.forEach(t->{
            AdvertPvLog log=saveAdvertPvLog(t);
            list.add(log);
        });
        return list;
    }

    @Async
    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogListAsync(List<AdvertPvLogDTO> request) {
        return saveAdvertPvLogList(request);
    }


    @Override
    public List<QyStatsAdvertByHotelVO> getQyStatsAdvertByHotelVOList(Long advertId) {
        List<QyStatsAdvertByHotelVO> vos=advertPvLogDomain.getQyStatsAdvertByHotelVOList(advertId);

        List<HotelVO> hotelVOS=hotelService.getHotelVOListByIdIn(vos.stream().map(t->t.getHotelId()).collect(Collectors.toList()));

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


    @Override
    public List<QyStatsAdvertPvForDeviceVO> getQyStatsAdvertPvForDeviceVOList(Date fromTime, Date toTime) {
        return advertPvLogDomain.getQyStatsAdvertPvForDeviceVOList(fromTime,toTime);
    }


}
