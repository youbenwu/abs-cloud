package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.service.HotelDeviceService;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertBuy;
import com.outmao.ebs.portal.entity.AdvertBuyDisplay;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.portal.vo.StatsAdvertStatusVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

@Slf4j
@Service
public class AdvertServiceImpl extends BaseService implements AdvertService {

    @Autowired
    private AdvertDomain advertDomain;

    @Autowired
    private AdvertChannelService advertChannelService;

    @Autowired
    private HotelDeviceService hotelDeviceService;


    @Override
    public Advert saveAdvert(AdvertDTO request) {
        return advertDomain.saveAdvert(request);
    }

    @Override
    public Advert setAdvertDisplay(SetAdvertDisplayDTO request) {
        return advertDomain.setAdvertDisplay(request);
    }

    @Override
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        return advertDomain.setAdvertStatus(request);
    }

    @Override
    public void deleteAdvertById(Long id) {
        advertDomain.deleteAdvertById(id);
    }

    @Override
    public Advert buy(Long id, AdvertBuy buy) {
        return advertDomain.buy(id,buy);
    }

    @Override
    public Advert buyDisplay(Long id, AdvertBuyDisplay buyDisplay) {
        return advertDomain.buyDisplay(id,buyDisplay);
    }

    @Override
    public AdvertVO getAdvertVOById(Long id) {
        return advertDomain.getAdvertVOById(id);
    }

    @Override
    public List<AdvertVO> getAdvertVOList(GetAdvertListDTO request) {
        return advertDomain.getAdvertVOList(request);
    }

    @Override
    public Page<AdvertVO> getAdvertVOPage(GetAdvertListDTO request, Pageable pageable) {
        return advertDomain.getAdvertVOPage(request,pageable);
    }

    @Override
    public List<AdvertVO> getAdvertVOList(GetAdvertListForHotelPadDTO request) {
        if(request.getSize()==null){
            request.setSize(100);
        }
        Pageable pageable = PageRequest.of(0,request.getSize(), Sort.Direction.ASC,"sort");
        Page<AdvertVO> page=getAdvertVOPage(request,pageable);
        return page.getContent();
    }

    @Override
    public Page<AdvertVO> getAdvertVOPage(GetAdvertListForHotelPadDTO request, Pageable pageable) {
        return getAdvertVOPage(getGetAdvertListDTO(request),pageable);
    }

    @Override
    public List<StatsAdvertStatusVO> getStatsAdvertStatusVOList(GetAdvertListDTO request) {
        return advertDomain.getStatsAdvertStatusVOList(request);
    }

    private GetAdvertListDTO getGetAdvertListDTO(GetAdvertListForHotelPadDTO request){

        HotelDeviceVO device=hotelDeviceService.getHotelDeviceVOByDeviceNo(request.getDeviceNo());

        GetAdvertListDTO listDTO=new GetAdvertListDTO();
        listDTO.setPlaceId(device.getHotelId());
        listDTO.setDisplay(true);
        listDTO.setSee(true);

        if(StringUtils.isEmpty(request.getChannelCode()!=null)){
            AdvertChannel channel=advertChannelService.getAdvertChannelByCode(request.getChannelCode());
            if(channel!=null){
                listDTO.setChannelId(channel.getId());
            }
        }
        return listDTO;
    }



    //每30分钟一次
    @Scheduled(cron = "0 0/30 * * * *")
    //打乱广告顺序
    public void randomAdvertSort(){
        advertDomain.randomAdvertSort();
    }


    //每天0时一次
    @Scheduled(cron = "0 0 0 * * *")
    //检测广告过期
    public void checkAdvertExpire(){
        advertDomain.checkAdvertExpire();
    }


}
