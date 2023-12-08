package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.common.constant.HotelRoomStatus;
import com.outmao.ebs.hotel.dao.HotelCustomerDao;
import com.outmao.ebs.hotel.dao.HotelCustomerStayDao;
import com.outmao.ebs.hotel.domain.HotelCustomerStayDomain;
import com.outmao.ebs.hotel.domain.HotelRoomDomain;
import com.outmao.ebs.hotel.domain.conver.HotelCustomerStayVOConver;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelCustomer;
import com.outmao.ebs.hotel.entity.HotelCustomerStay;
import com.outmao.ebs.hotel.entity.HotelRoom;
import com.outmao.ebs.hotel.entity.QHotelCustomerStay;
import com.outmao.ebs.hotel.vo.HotelCustomerStayVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.Calendar;
import java.util.Date;


@Component
public class HotelCustomerStayDomainImpl extends BaseDomain implements HotelCustomerStayDomain {

    @Autowired
    private HotelCustomerDao hotelCustomerDao;

    @Autowired
    private HotelCustomerStayDao hotelCustomerStayDao;


    @Autowired
    private HotelRoomDomain hotelRoomDomain;

    private HotelCustomerStayVOConver hotelCustomerStayVOConver=new HotelCustomerStayVOConver();





    @Transactional()
    @Override
    public HotelCustomerStay saveHotelCustomerStay(HotelCustomerStayDTO request) {


        if(request.getStartTime()==null){
            request.setStartTime(new Date());
        }


        HotelCustomerStay stay=request.getId()==null?null:hotelCustomerStayDao.findByIdForUpdate(request.getId());

        if(stay==null){
            stay=new HotelCustomerStay();
            HotelCustomer customer=hotelCustomerDao.getOne(request.getCustomerId());
            stay.setOrgId(customer.getOrgId());
            stay.setHotel(customer.getHotel());
            stay.setUserId(customer.getUserId());
            stay.setCustomerId(customer.getId());
            stay.setCreateTime(new Date());
        }


        BeanUtils.copyProperties(request,stay,"id","customerId");

        stay.setUpdateTime(new Date());


        Calendar end=Calendar.getInstance();
        end.setTime(stay.getStartTime());
        end.add(Calendar.DAY_OF_YEAR,stay.getStayDays());
        //中午12点正为退房时间
        end.set(Calendar.HOUR_OF_DAY, 12);
        end.set(Calendar.MINUTE,0);
        end.set(Calendar.SECOND,0);

        stay.setEndTime(end.getTime());

        stay.setKeyword(getKeyword(stay));

        hotelCustomerStayDao.save(stay);


        if(stay.getStatus()==HotelCustomerStay.STATUS_STAY_NOT_IN) {
            Calendar today = Calendar.getInstance();
            Calendar start = Calendar.getInstance();
            start.setTime(stay.getStartTime());
            if (today.get(Calendar.DAY_OF_YEAR) == start.get(Calendar.DAY_OF_YEAR)) {
                SetHotelCustomerStayStatusDTO stayStatusDTO = new SetHotelCustomerStayStatusDTO();
                stayStatusDTO.setId(stay.getId());
                stayStatusDTO.setStatus(HotelCustomerStay.STATUS_STAY_IN);
                stayStatusDTO.setStatusRemark("已入住");
                setHotelCustomerStayStatus(stayStatusDTO);
            }
        }

        return stay;
    }


    private String getKeyword(HotelCustomerStay data){
        StringBuffer s=new StringBuffer();

        s.append(data.getRoomNo());

        if(!StringUtils.isEmpty(data.getName())){
            s.append(" "+data.getName());
        }

        if(!StringUtils.isEmpty(data.getPhone())){
            s.append(" "+data.getPhone());
        }

        return s.toString();
    }



    @Transactional()
    @Override
    public HotelCustomerStay setHotelCustomerStayStatus(SetHotelCustomerStayStatusDTO request) {

        HotelCustomerStay stay=hotelCustomerStayDao.findByIdForUpdate(request.getId());

        if(stay.getStatus()==request.getStatus()){
            return stay;
        }

        if(stay.getStatus()==HotelCustomerStay.STATUS_STAY_OUT){
            throw new BusinessException("状态异常");
        }

        BeanUtils.copyProperties(request,stay);
        //stay.setStatus(request.getStatus());
        //stay.setStatusRemark(request.getStatusRemark());


        if(stay.getStatus()==HotelCustomerStay.STATUS_STAY_IN){
            stayIn(stay);
        }

        if(stay.getStatus()==HotelCustomerStay.STATUS_STAY_OUT){

            stayOut(stay);
        }

        hotelCustomerStayDao.save(stay);

        return stay;
    }


    //入住处理
    private void stayIn(HotelCustomerStay stay){
        //状态数据统计
        HotelCustomer customer =hotelCustomerDao.findByIdForUpdate(stay.getCustomerId());
        if(customer.getStayStatus()==HotelCustomer.STATUS_STAY_IN){
            throw new BusinessException("客户已是入住状态，不可重复入住");
        }
        customer.setStayStatus(HotelCustomer.STATUS_STAY_IN);
        customer.setStays(customer.getStays()+1);
        hotelCustomerDao.save(customer);

        HotelRoom room=hotelRoomDomain.getHotelRoom(stay.getHotel().getId(),stay.getRoomNo());

        if(room==null){
            throw new BusinessException("房间号不存在");
        }

        SetHotelRoomStatusDTO roomStatusDTO=new SetHotelRoomStatusDTO();
        roomStatusDTO.setId(room.getId());
        roomStatusDTO.setStatus(HotelRoomStatus.Stay.getStatus());
        roomStatusDTO.setStatusRemark("房客："+stay.getName()+" "+stay.getPhone());
        hotelRoomDomain.setHotelRoomStatus(roomStatusDTO);


    }

    //退房处理
    private void stayOut(HotelCustomerStay stay){

        //状态数据统计
        HotelCustomer customer =hotelCustomerDao.findByIdForUpdate(stay.getCustomerId());
        customer.setStayStatus(HotelCustomer.STATUS_STAY_NOT_IN);
        customer.setStayDays(customer.getStayDays()+stay.getStayDays());
        customer.setAmount(customer.getAmount()+stay.getAmount());
        hotelCustomerDao.save(customer);

        HotelRoom room=hotelRoomDomain.getHotelRoom(stay.getHotel().getId(),stay.getRoomNo());

        if(room==null){
            throw new BusinessException("房间号不存在");
        }

        SetHotelRoomStatusDTO roomStatusDTO=new SetHotelRoomStatusDTO();
        roomStatusDTO.setId(room.getId());
        roomStatusDTO.setStatus(HotelRoomStatus.Idle.getStatus());
        roomStatusDTO.setStatusRemark("空闲");
        hotelRoomDomain.setHotelRoomStatus(roomStatusDTO);

    }


    @Override
    public Page<HotelCustomerStayVO> getHotelCustomerStayVOPage(GetHotelCustomerStayListDTO request, Pageable pageable) {
        QHotelCustomerStay e=QHotelCustomerStay.hotelCustomerStay;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        if(request.getStatus()!=null){
            p=e.status.eq(request.getStatus()).and(p);
        }

        Page<HotelCustomerStayVO> page=queryPage(e,p,hotelCustomerStayVOConver,pageable);

        return page;
    }


    //每天中午12点自动退房
    //cron表达式
    //秒 分 时 日 月 周几
//    @Scheduled(cron = "0 0 12 * * ?")
//    public void autoStayOut(){
//        Calendar now=Calendar.getInstance();
//        QHotelCustomerStay e=QHotelCustomerStay.hotelCustomerStay;
//        Predicate p=e.status.eq(HotelCustomerStay.STATUS_STAY_IN).and(e.endTime.dayOfYear().eq(now.getSubStatus(Calendar.DAY_OF_YEAR)));
//        hotelCustomerStayDao.findAll(p).forEach((HotelCustomerStay stay)->{
//            SetHotelCustomerStayStatusDTO statusDTO=new SetHotelCustomerStayStatusDTO();
//            statusDTO.setId(stay.getId());
//            statusDTO.setStatus(HotelCustomerStay.STATUS_STAY_OUT);
//            statusDTO.setStatusRemark("已退房");
//            try {
//                setHotelCustomerStayStatus(statusDTO);
//            }catch (Exception ex){
//                ex.printStackTrace();
//            }
//        });
//    }


}
