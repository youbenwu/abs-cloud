package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.hotel.common.constant.LeaseStatus;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceVOConver;
import com.outmao.ebs.hotel.dto.HotelDeviceLeaseDTO;
import com.outmao.ebs.hotel.dto.GetHotelDeviceListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceBuyDTO;
import com.outmao.ebs.hotel.entity.*;
import com.outmao.ebs.hotel.vo.HotelDeviceVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceCityVO;
import com.outmao.ebs.hotel.vo.StatsHotelDeviceProvinceVO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        HotelDevice device=request.getId()==null?hotelDeviceDao.findByDeviceNoLock(request.getDeviceNo())
                :hotelDeviceDao.findByIdLock(request.getId());

        if(device==null){
            Hotel hotel=hotelDao.getOne(request.getHotelId());
            device=getHotelDeviceByNew();
            device.setHotelId(request.getHotelId());
            device.setOrgId(hotel.getOrgId());
            device.setStatus(1);
            if(hotel.getContact()!=null&&hotel.getContact().getAddress()!=null) {
                device.setProvince(hotel.getContact().getAddress().getProvince());
                device.setCity(hotel.getContact().getAddress().getCity());
            }
        }

        BeanUtils.copyProperties(request,device,"id","hotelId");
        device.setKeyword(getKeyword(device));
        device.setUpdateTime(new Date());

        hotelDeviceDao.save(device);

        return device;
    }

    private HotelDevice newHotelDevice(){
        HotelDevice device = new HotelDevice();
        device.setBuy(new HotelDeviceBuy());
        device.setLease(new HotelDeviceLease());
        device.setCreateTime(new Date());
        device.setUpdateTime(new Date());
        return device;
    }


    private HotelDevice getHotelDeviceByNew(){
        List<HotelDevice> list=hotelDeviceDao.findAllByNoActivateLock(PageRequest.of(0,1));
        if(list.isEmpty())
            return newHotelDevice();
        return list.get(0);
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

    @Override
    public long getHotelDeviceCountByPartnerId(Long partnerId) {
        return hotelDeviceDao.countByBuyPartnerId(partnerId);
    }


    @Override
    public long getHotelDeviceCountByLeaseRenterId(Long leaseRenterId) {
        return hotelDeviceDao.countByLeaseRenterId(leaseRenterId);
    }

    @Transactional()
    @Override
    public void deleteHotelDeviceById(Long id) {
        HotelDevice device=hotelDeviceDao.getOne(id);
        hotelDeviceDao.delete(device);
    }

    @Override
    public HotelDevice getHotelDeviceByUserId(Long userId) {
        return hotelDeviceDao.findByUserId(userId);
    }


    @Override
    public List<HotelDevice> getHotelDeviceListByOwnerId(Long ownerId) {
        return hotelDeviceDao.findAllByBuyOwnerId(ownerId);
    }

    @Override
    public List<HotelDevice> getHotelDeviceListByPartnerId(Long partnerId) {
        return hotelDeviceDao.findAllByBuyPartnerId(partnerId);
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
    public List<HotelDeviceVO> getHotelDeviceVOList(GetHotelDeviceListDTO request) {

        QHotelDevice e=QHotelDevice.hotelDevice;

        Predicate p=getPredicate(request);

        return queryList(e,p,hotelDeviceVOConver);
    }

    @Override
    public Page<HotelDeviceVO> getHotelDeviceVOPage(GetHotelDeviceListDTO request, Pageable pageable) {

        QHotelDevice e=QHotelDevice.hotelDevice;

        Predicate p=getPredicate(request);

        Page<HotelDeviceVO> page=queryPage(e,p,hotelDeviceVOConver,pageable);


        return page;
    }

    private Predicate getPredicate(GetHotelDeviceListDTO request){
        QHotelDevice e=QHotelDevice.hotelDevice;
        Predicate p=null;
        if(request.getStatus()!=null){
            p=e.status.eq(1);
        }
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%").and(p);
        }

        if(request.getHotelId()!=null){
            p=e.hotelId.eq(request.getHotelId()).and(p);
        }

        if(request.getRenterId()!=null){
            p=e.lease.renterId.eq(request.getRenterId()).and(p);
        }

        return p;
    }

    @Override
    public List<StatsHotelDeviceCityVO> getStatsHotelDeviceCityVOList(Integer size) {
        QHotelDevice e=QHotelDevice.hotelDevice;
        List<Tuple> list=QF.select(e.buy.amount.sum(),e.count(),e.city).groupBy(e.city).from(e).where(e.city.isNotEmpty()).limit(size==null?10000:size).orderBy(e.count().desc()).fetch();

        List<StatsHotelDeviceCityVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsHotelDeviceCityVO vo=new StatsHotelDeviceCityVO();
            Double amount=t.get(e.buy.amount.sum());
            vo.setCity(t.get(e.city));
            vo.setCount(t.get(e.count()));
            vo.setAmount(amount==null?0:amount);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public List<StatsHotelDeviceProvinceVO> getStatsHotelDeviceProvinceVOList(Integer size) {
        QHotelDevice e=QHotelDevice.hotelDevice;
        List<Tuple> list=QF.select(e.buy.amount.sum(),e.count(),e.province).groupBy(e.province).from(e).where(e.province.isNotEmpty()).limit(size==null?10000:size).orderBy(e.count().desc()).fetch();

        List<StatsHotelDeviceProvinceVO> vos=new ArrayList<>(list.size());

        list.forEach(t->{
            StatsHotelDeviceProvinceVO vo=new StatsHotelDeviceProvinceVO();
            vo.setProvince(t.get(e.province));
            vo.setCount(t.get(e.count()));
            vo.setAmount(t.get(e.buy.amount.sum()));
            vos.add(vo);
        });
        return vos;
    }

    @Transactional()
    @Override
    public List<HotelDevice> buy(HotelDeviceBuyDTO request) {
        List<HotelDevice> devices=newHotelDeviceList(request.getQuantity());

        devices.forEach(d->{
            if(d.getBuy()==null){
                d.setBuy(new HotelDeviceBuy());
            }
            d.getBuy().setOwnerId(request.getUserId());
            d.getBuy().setPartnerId(request.getPartnerId());
            d.getBuy().setAmount(request.getPrice());
        });

        hotelDeviceDao.saveAll(devices);

        return devices;
    }

    @Transactional()
    @Override
    public List<HotelDevice> lease(HotelDeviceLeaseDTO request) {
        List<HotelDevice> devices=getHotelDeviceListByNoLeaseLock(request.getQuantity());

        devices.forEach(d->{
            if(d.getLease()==null){
                d.setLease(new HotelDeviceLease());
            }
            d.getLease().setStatus(LeaseStatus.LeaseIng.getStatus());
            d.getLease().setRenterId(request.getUserId());
            d.getLease().setPartnerId(request.getPartnerId());
            d.getLease().setStartTime(request.getStartTime());
            d.getLease().setEndTime(request.getEndTime());
            d.getLease().setTotalRent(d.getLease().getTotalRent()+request.getPrice());
        });

        hotelDeviceDao.saveAll(devices);

        return devices;
    }


    private List<HotelDevice> getHotelDeviceListByNoLeaseLock(int size){
        List<HotelDevice> devices=hotelDeviceDao.findAllByLeaseStatusInLock(new Integer[]{LeaseStatus.NoLease.getStatus(),LeaseStatus.LeaseExpire.getStatus()},PageRequest.of(0,size));
        if(devices.size()<size){
            devices.addAll(newHotelDeviceList(size-devices.size()));
        }
        return devices;
    }


    private List<HotelDevice> newHotelDeviceList(int size) {
        List<HotelDevice> list=new ArrayList<>(size);
        for (int i=0;i<size;i++){
            HotelDevice device=new HotelDevice();
            list.add(device);
        }
        return list;
    }


    @Transactional()
    @Override
    public List<HotelDevice> checkLeaseExpire() {
        Date time=DateUtil.addDays(new Date(),-1);
        List<HotelDevice> list=hotelDeviceDao.findAllByLeaseExpireLock(time);
        list.forEach(d->{
            d.getLease().setStatus(LeaseStatus.LeaseExpire.getStatus());
        });
        hotelDeviceDao.saveAll(list);
        return list;
    }



}
