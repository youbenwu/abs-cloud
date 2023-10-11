package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.dao.HotelCustomerDao;
import com.outmao.ebs.hotel.dao.HotelCustomerStayDao;
import com.outmao.ebs.hotel.dao.HotelDao;
import com.outmao.ebs.hotel.domain.HotelCustomerDomain;
import com.outmao.ebs.hotel.domain.HotelRoomDomain;
import com.outmao.ebs.hotel.domain.conver.HotelCustomerVOConver;
import com.outmao.ebs.hotel.dto.*;
import com.outmao.ebs.hotel.entity.HotelCustomer;
import com.outmao.ebs.hotel.entity.QHotelCustomer;
import com.outmao.ebs.hotel.vo.HotelCustomerVO;
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
public class HotelCustomerDomainImpl extends BaseDomain implements HotelCustomerDomain {

    @Autowired
    private HotelCustomerDao hotelCustomerDao;

    @Autowired
    private HotelCustomerStayDao hotelCustomerStayDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelRoomDomain hotelRoomDomain;

    private HotelCustomerVOConver hotelCustomerVOConver=new HotelCustomerVOConver();



    @Transactional()
    @Override
    public HotelCustomer saveHotelCustomer(HotelCustomerDTO request) {


        HotelCustomer customer=request.getId()==null?null:hotelCustomerDao.findByIdForUpdate(request.getId());

        if(customer==null){
            if(hotelCustomerDao.findByHotelIdAndPhone(request.getHotelId(),request.getPhone())!=null){
                throw new BusinessException("客户已存在");
            }
            customer=new HotelCustomer();
            customer.setCreateTime(new Date());
            customer.setHotel(hotelDao.getOne(request.getHotelId()));
            customer.setOrgId(customer.getHotel().getOrgId());
            customer.setUserId(request.getUserId());
        }

        BeanUtils.copyProperties(request,customer,"userId");
        customer.setUpdateTime(new Date());

        customer.setKeyword(getKeyword(customer));

        hotelCustomerDao.save(customer);

        return customer;
    }


    public String getKeyword(HotelCustomer data){
        StringBuffer s=new StringBuffer();

        s.append(data.getUserId());

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
    public void deleteHotelCustomerById(Long id) {
        HotelCustomer customer=hotelCustomerDao.getOne(id);
        hotelCustomerDao.delete(customer);
    }

    @Override
    public HotelCustomer getHotelCustomerByHotelIdAndPhone(Long hotelId, String phone) {
        return hotelCustomerDao.findByHotelIdAndPhone(hotelId,phone);
    }

    @Override
    public HotelCustomerVO getHotelCustomerVOById(Long id) {
        QHotelCustomer e=QHotelCustomer.hotelCustomer;
        HotelCustomerVO vo=queryOne(e,e.id.eq(id),hotelCustomerVOConver);
        return vo;
    }



    @Override
    public HotelCustomerVO getHotelCustomerVO(Long hotelId, Long userId) {
        QHotelCustomer e=QHotelCustomer.hotelCustomer;
        HotelCustomerVO vo=queryOne(e,e.hotel.id.eq(hotelId).and(e.userId.eq(userId)),hotelCustomerVOConver);
        return vo;
    }

    @Override
    public HotelCustomerVO getHotelCustomerVOByHotelIdAndPhone(Long hotelId, String phone) {
        QHotelCustomer e=QHotelCustomer.hotelCustomer;
        HotelCustomerVO vo=queryOne(e,e.hotel.id.eq(hotelId).and(e.phone.eq(phone)),hotelCustomerVOConver);
        return vo;
    }

    @Override
    public Page<HotelCustomerVO> getHotelCustomerVOPage(GetHotelCustomerListDTO request, Pageable pageable) {

        QHotelCustomer e=QHotelCustomer.hotelCustomer;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(request.getHotelId()!=null){
            p=e.hotel.id.eq(request.getHotelId()).and(p);
        }

        if(request.getStayStatus()!=null){
            p=e.stayStatus.eq(request.getStayStatus()).and(p);
        }

        Page<HotelCustomerVO> page=queryPage(e,p,hotelCustomerVOConver,pageable);

        return page;
    }





}
