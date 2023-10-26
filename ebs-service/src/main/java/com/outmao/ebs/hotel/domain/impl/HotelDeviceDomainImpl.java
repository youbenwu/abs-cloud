package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceVOConver;
import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceNewDTO;
import com.outmao.ebs.hotel.entity.Hotel;
import com.outmao.ebs.hotel.entity.HotelDevice;
import com.outmao.ebs.hotel.entity.QHotelDevice;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
            Hotel hotel=hotelDao.getOne(request.getHotelId());
            device=getHotelDeviceByNew();
            if(device==null) {
                device = new HotelDevice();
                device.setCreateTime(new Date());
            }
            device.setHotelId(request.getHotelId());
            device.setOrgId(hotel.getOrgId());
            device.setStatus(1);
            if(hotel.getContact()!=null&&hotel.getContact().getAddress()!=null) {
                device.setProvince(hotel.getContact().getAddress().getProvince());
                device.setCity(hotel.getContact().getAddress().getCity());
            }
        }

        BeanUtils.copyProperties(request,device,"id","hotelId");
        device.setUpdateTime(new Date());

        device.setKeyword(getKeyword(device));

        hotelDeviceDao.save(device);

        return device;
    }


    private HotelDevice getHotelDeviceByNew(){
        try{
            QHotelDevice e=QHotelDevice.hotelDevice;
            HotelDevice device=QF.select(e).from(e).where(e.status.eq(0)).fetchFirst();
            if(device!=null){
                device=hotelDeviceDao.findByIdForUpdate(device.getId());
                if(device.getStatus()!=0){
                    Thread.sleep(100);
                    return getHotelDeviceByNew();
                }
            }
            return device;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("服务器繁忙，请稍候再试！");
        }
    }

    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(HotelDeviceNewDTO request) {
        HotelDevice device=new HotelDevice();
        device.setCreateTime(new Date());
        device.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,device);
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
            p=e.hotelId.eq(request.getHotelId()).and(p);
        }

        Page<HotelDeviceVO> page=queryPage(e,p,hotelDeviceVOConver,pageable);


        return page;
    }

    @Override
    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size) {
        QHotelDevice e=QHotelDevice.hotelDevice;
        List<Tuple> list=QF.select(e.amount.sum(),e.count(),e.city).groupBy(e.city).from(e).where(e.city.isNotEmpty()).limit(size==null?10000:size).orderBy(e.count().desc()).fetch();

        List<StatsHotelDeviceCityVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsHotelDeviceCityVO vo=new StatsHotelDeviceCityVO();
            vo.setCity(t.get(e.city));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.amount.sum()));
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size) {
        QHotelDevice e=QHotelDevice.hotelDevice;
        List<Tuple> list=QF.select(e.amount.sum(),e.count(),e.province).groupBy(e.province).from(e).where(e.province.isNotEmpty()).limit(size==null?10000:size).orderBy(e.count().desc()).fetch();

        List<StatsHotelDeviceProvinceVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsHotelDeviceProvinceVO vo=new StatsHotelDeviceProvinceVO();
            vo.setProvince(t.get(e.province));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.amount.sum()));
            vos.add(vo);
        });
        return vos;
    }


}
