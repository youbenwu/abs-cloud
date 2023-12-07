package com.outmao.ebs.hotel.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.HotelDeviceOwnerDomain;
import com.outmao.ebs.hotel.dto.GetHotelDeviceOwnerListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceBuyDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerBuyDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.service.HotelDeviceBuyService;
import com.outmao.ebs.hotel.vo.HotelDeviceOwnerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class HotelDeviceBuyServiceImpl extends BaseService implements HotelDeviceBuyService {


    @Autowired
    private HotelDeviceOwnerDomain hotelDeviceOwnerDomain;

    @Autowired
    private HotelDeviceDomain hotelDeviceDomain;

    @Transactional()
    @Override
    public HotelDeviceOwner saveHotelDeviceOwner(HotelDeviceOwnerDTO request) {
        HotelDeviceBuyDTO buyDTO=new HotelDeviceBuyDTO();
        BeanUtils.copyProperties(request,buyDTO);
        hotelDeviceDomain.buy(buyDTO);
        return hotelDeviceOwnerDomain.saveHotelDeviceOwner(request);
    }

    @Override
    public HotelDeviceOwner saveHotelDeviceOwnerBuy(HotelDeviceOwnerBuyDTO request) {

        return hotelDeviceOwnerDomain.saveHotelDeviceOwnerBuy(request);
    }

    @Override
    public HotelDeviceOwner getHotelDeviceOwnerByUserId(Long userId) {
        return hotelDeviceOwnerDomain.getHotelDeviceOwnerByUserId(userId);
    }

    @Override
    public HotelDeviceOwnerVO getHotelDeviceOwnerVOByUserId(Long userId) {
        return hotelDeviceOwnerDomain.getHotelDeviceOwnerVOByUserId(userId);
    }

    @Override
    public Page<HotelDeviceOwnerVO> getHotelDeviceOwnerVOPage(GetHotelDeviceOwnerListDTO request, Pageable pageable) {
        return hotelDeviceOwnerDomain.getHotelDeviceOwnerVOPage(request,pageable);
    }

}
