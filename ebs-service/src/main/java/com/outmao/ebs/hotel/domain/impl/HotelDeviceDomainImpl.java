package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.util.DateUtil;
import com.outmao.ebs.common.vo.Between;
import com.outmao.ebs.common.vo.TimeSpan;
import com.outmao.ebs.hotel.common.constant.HotelDeviceStatus;
import com.outmao.ebs.hotel.common.constant.LeaseStatus;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.hotel.dao.HotelDeviceLeaseRecordDao;
import com.outmao.ebs.hotel.dao.HotelRoomDao;
import com.outmao.ebs.hotel.domain.HotelDeviceDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceVOConver;
import com.outmao.ebs.hotel.dto.*;
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
import org.springframework.util.Assert;
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

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private HotelDeviceLeaseRecordDao hotelDeviceLeaseRecordDao;

    private HotelDeviceVOConver hotelDeviceVOConver=new HotelDeviceVOConver();



    @Transactional()
    @Override
    public HotelDevice saveHotelDevice(HotelDeviceDTO request) {
        Assert.notNull(request.getHotelId(),"酒店ID不能为空");
        Assert.notNull(request.getRoomNo(),"房间号不能为空");
        Assert.notNull(request.getDeviceNo(),"设备号不能为空");

        HotelDevice device=request.getId()==null?hotelDeviceDao.findByDeviceNoLock(request.getDeviceNo())
                :hotelDeviceDao.findByIdLock(request.getId());

        if(device==null){
            //未激活

            device=hotelDeviceDao.findByHotelIdAndRoomNoLock(request.getHotelId(),request.getRoomNo());

            if(device!=null&&device.getStatus()==1){
                //一个房间只能激活一个设备
                throw new BusinessException("房间已有设备");
            }

            if(device==null){
                if(!hotelRoomDao.existsByHotelIdAndRoomNo(request.getHotelId(),request.getRoomNo())){
                    throw new BusinessException("房间不存在");
                }
                device=getHotelDeviceByNew();
                setHotelRoom(device,request.getHotelId(),request.getRoomNo());
            }

            //绑定设备号激活
            device.setDeviceNo(request.getDeviceNo());
            //绑定激活人
            device.setActUserId(request.getActUserId());
            //设置状态
            device.setStatus(1);
            device.setActiveTime(new Date());


        }

        BeanUtils.copyProperties(request,device,"id","hotelId","roomNo","deviceNo","actUserId");

        if(device.getLease()!=null&&device.getLease().getStatus()==1){
            if(device.getLease().getEndTime()==null){
                //计算开始结束时间
                leaseStart(device);
            }
        }

        device.setKeyword(getKeyword(device));
        device.setUpdateTime(new Date());

        hotelDeviceDao.save(device);

        return device;
    }


    private void setHotelRoom(HotelDevice device,Long hotelId,String roomNo){
        Hotel hotel=hotelDao.getOne(hotelId);
        device.setOrgId(hotel.getOrgId());
        device.setHotelId(hotelId);
        device.setRoomNo(roomNo);
        if(hotel.getContact()!=null&&hotel.getContact().getAddress()!=null) {
            device.setProvince(hotel.getContact().getAddress().getProvince());
            device.setCity(hotel.getContact().getAddress().getCity());
        }
    }

    @Override
    public HotelDevice getHotelDeviceById(Long id) {
        return hotelDeviceDao.findById(id).orElse(null);
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
        if(request.getStatus()!=null&&request.getStatus().size()>0){
            p=e.status.in(request.getStatus()).and(p);
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

        if(request.getActiveStatus()!=null){
            p=e.activeStatus.eq(request.getActiveStatus()).and(p);
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

        //List<HotelDevice> devices=getHotelDeviceListByNoLeaseLock(request.getQuantity());

        //租赁新创建设备，暂不做续租
        List<HotelDevice> devices=newHotelDeviceList(request.getQuantity());

        devices.forEach(d->{
            if(d.getLease()==null){
                d.setLease(new HotelDeviceLease());
            }
            d.getLease().setStatus(LeaseStatus.LeaseIng.getStatus());
            d.getLease().setRenterId(request.getUserId());
            d.getLease().setPartnerId(request.getPartnerId());
            d.getLease().setYears(request.getYears());
            d.getLease().setStartTime(request.getStartTime());
            d.getLease().setEndTime(request.getEndTime());
            d.getLease().setAmount(request.getPrice());
            d.getLease().setTotalYears(d.getLease().getTotalYears()+request.getYears());
            d.getLease().setTotalAmount(d.getLease().getTotalAmount()+request.getPrice());
            //设备为未托管状态 等待用户选择酒店房间托管
            d.setStatus(HotelDeviceStatus.NoDeploy.getStatus());

        });

        hotelDeviceDao.saveAll(devices);

        devices.forEach(d->{
            saveHotelDeviceLeaseRecord(d);
        });

        return devices;
    }

    private void leaseStart(HotelDevice device){
        //计算开始结束时间
        TimeSpan span=new TimeSpan();
        span.setField(TimeSpan.YEAR);
        span.setValue(device.getLease().getYears());
        Between<Date> between=span.getDateBetween(new Date());
        device.getLease().setStartTime(between.getFrom());
        device.getLease().setEndTime(between.getTo());
        HotelDeviceLeaseRecord record=hotelDeviceLeaseRecordDao.findByDeviceIdAndUserIdLock(device.getId(),device.getLease().getRenterId());
        if(record!=null){
            record.setStartTime(device.getLease().getStartTime());
            record.setEndTime(device.getLease().getEndTime());
            hotelDeviceLeaseRecordDao.save(record);
        }
    }

    private HotelDeviceLeaseRecord saveHotelDeviceLeaseRecord(HotelDevice device){
        HotelDeviceLeaseRecordDTO recordDTO=new HotelDeviceLeaseRecordDTO();
        recordDTO.setUserId(device.getLease().getRenterId());
        recordDTO.setPartnerId(device.getLease().getPartnerId());
        recordDTO.setDeviceId(device.getId());
        recordDTO.setYears(device.getLease().getYears());
        recordDTO.setAmount(device.getLease().getAmount());
        recordDTO.setStartTime(device.getLease().getStartTime());
        recordDTO.setEndTime(device.getLease().getEndTime());
        return saveHotelDeviceLeaseRecord(recordDTO);
    }

    private HotelDeviceLeaseRecord saveHotelDeviceLeaseRecord(HotelDeviceLeaseRecordDTO request){
        HotelDeviceLeaseRecord record=hotelDeviceLeaseRecordDao.findByDeviceIdAndUserIdLock(request.getDeviceId(),request.getUserId());
        if(record==null){
            record=new HotelDeviceLeaseRecord();
            record.setCreateTime(new Date());
        }

        BeanUtils.copyProperties(request,record);
        record.setTotalAmount(record.getTotalAmount()+record.getAmount());
        record.setTotalYears(record.getTotalYears()+record.getYears());
        record.setUpdateTime(new Date());

        hotelDeviceLeaseRecordDao.save(record);

        return record;
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
            HotelDevice device=newHotelDevice();
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
            HotelDeviceLeaseRecord record=hotelDeviceLeaseRecordDao.findByDeviceIdAndUserIdLock(d.getId(),d.getLease().getRenterId());
            if(record!=null){
                record.setStatus(LeaseStatus.LeaseExpire.getStatus());
                hotelDeviceLeaseRecordDao.save(record);
            }
        });
        hotelDeviceDao.saveAll(list);
        return list;
    }


    @Transactional()
    @Override
    public List<HotelDevice> deploy(List<HotelRoomDeviceDeployDTO> request) {
        List<HotelDevice> list=new ArrayList<>(request.size());
        for (HotelRoomDeviceDeployDTO deploy : request) {
            HotelDevice device=hotelDeviceDao.findByIdLock(deploy.getDeviceId());
            if(device==null){
                throw new BusinessException("设备ID不存在");
            }
            if(device.getStatus()!=HotelDeviceStatus.NoDeploy.getStatus()){
                throw new BusinessException("设备已被托管");
            }
            setHotelRoom(device,deploy.getHotelId(),deploy.getRoomNo());
            device.setStatus(HotelDeviceStatus.Deploy.getStatus());
            list.add(device);
        }
        hotelDeviceDao.saveAll(list);
        return list;
    }



}
