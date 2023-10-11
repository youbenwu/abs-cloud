package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceVOConver;
import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceDTO;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.entity.QHotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


@Component
public class HotelDeviceDomainImpl extends BaseDomain implements HotelDeviceDomain {


    @Autowired
    private HotelDeviceDao hotelDeviceDao;

    @Autowired
    private HotelDao hotelDao;

    private HotelDeviceVOConver hotelDeviceVOConver=new HotelDeviceVOConver();


    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(HotelDeviceDTO request) {
        HotelDevice device=request.getId()==null?hotelDeviceDao.findByDeviceNo(request.getDeviceNo())
                :hotelDeviceDao.getOne(request.getId());


        if(device==null){
            device=new HotelDevice();
            device.setHotel(hotelDao.getOne(request.getHotelId()));
            device.setOrgId(device.getHotel().getOrgId());
            device.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,device,"id");
        device.setUpdateTime(new Date());

        device.setKeyword(getKeyword(device));

        hotelDeviceDao.save(device);

        return device;
    }


    private String getKeyword(HotelDevice device){
        StringBuffer s=new StringBuffer();

        if(device.getRoomNo()!=null) {
            s.append(" "+device.getRoomNo());
        }

        if(!StringUtils.isEmpty(device.getName())){
            s.append(" "+device.getName());
        }

        if(!StringUtils.isEmpty(device.getModel())){
            s.append(" "+device.getModel());
        }
        if(!StringUtils.isEmpty(device.getOs())){
            s.append(" "+device.getOs());
        }

        return s.toString();
    }


    @Override
    public long getHotelDeviceCount() {
        return hotelDeviceDao.count();
    }

    @Transactional()
    @Override
    public void deleteHotelDeviceById(Long id) {
        HotelDevice device=hotelDeviceDao.getOne(id);
        hotelDeviceDao.delete(device);
    }

    @Override
    public HotelDeviceVO getHotelDeviceVOById(Long id) {
        QHotelDevice e=QHotelDevice.hotelDevice;

        HotelDeviceVO vo=queryOne(e,e.id.eq(id),hotelDeviceVOConver);

        return vo;
    }

    @Override
    public HotelDeviceVO getHotelDeviceVOByDeviceNo(String deviceNo) {
        QHotelDevice e=QHotelDevice.hotelDevice;

        HotelDeviceVO vo=queryOne(e,e.deviceNo.eq(deviceNo),hotelDeviceVOConver);

        return vo;
    }

    @Override
    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable) {

        QHotelDevice e=QHotelDevice.hotelDevice;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        Page<HotelDeviceVO> page=queryPage(e,p,hotelDeviceVOConver,pageable);


        return page;
    }


}
