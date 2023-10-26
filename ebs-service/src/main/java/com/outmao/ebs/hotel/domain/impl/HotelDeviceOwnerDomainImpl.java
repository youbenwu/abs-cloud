package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.hotel.dao.HotelDeviceOwnerDao;
import com.outmao.ebs.hotel.domain.HotelDeviceOwnerDomain;
import com.outmao.ebs.hotel.dto.GetHotelDeviceOwnerListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceOwnerDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceOwner;
import com.outmao.ebs.hotel.entity.QHotelDeviceOwner;
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
public class HotelDeviceOwnerDomainImpl extends BaseDomain implements HotelDeviceOwnerDomain {


    @Autowired
    private HotelDeviceOwnerDao hotelDeviceOwnerDao;


    @Transactional()
    @Override
    public HotelDeviceOwner saveHotelDeviceOwner(HotelDeviceOwnerDTO request) {
        HotelDeviceOwner owner=hotelDeviceOwnerDao.findByUserId(request.getUserId());
        if(owner==null){
            owner=new HotelDeviceOwner();
            owner.setCreateTime(new Date());
            owner.setUserId(request.getUserId());
        }

        if(request.getId()==null){
            owner.setAmount(owner.getAmount()+request.getAmount());
            owner.setQuantity(owner.getQuantity()+request.getQuantity());
        }else{
            owner.setAmount(request.getAmount());
            owner.setQuantity(request.getQuantity());
        }

        BeanUtils.copyProperties(request,owner,"id","userId","quantity","amount");

        owner.setUpdateTime(new Date());

        owner.setKeyword(getKeyword(owner));

        hotelDeviceOwnerDao.save(owner);

        return owner;
    }




    public String getKeyword(HotelDeviceOwner data){
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
    public HotelDeviceOwner addHotelDeviceOwnerIncome(Long userId, double addIncome) {
        HotelDeviceOwner owner=hotelDeviceOwnerDao.findByUserId(userId);
        if(owner==null){
            throw new BusinessException("对象不存在");
        }

        owner.setIncome(owner.getIncome()+addIncome);

        hotelDeviceOwnerDao.save(owner);

        return owner;
    }

    @Override
    public HotelDeviceOwner getHotelDeviceOwnerByUserId(Long userId) {
        return hotelDeviceOwnerDao.findByUserId(userId);
    }

    @Override
    public Page<HotelDeviceOwner> getHotelDeviceOwnerPage(GetHotelDeviceOwnerListDTO request, Pageable pageable) {

        QHotelDeviceOwner e=QHotelDeviceOwner.hotelDeviceOwner;

        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        if(p!=null){
            return hotelDeviceOwnerDao.findAll(p,pageable);
        }

        return hotelDeviceOwnerDao.findAll(pageable);
    }


}
