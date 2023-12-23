package com.outmao.ebs.hotel.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.hotel.dao.HotelDeviceRenterDao;
import com.outmao.ebs.hotel.domain.HotelDeviceRenterDomain;
import com.outmao.ebs.hotel.domain.conver.HotelDeviceRenterVOConver;
import com.outmao.ebs.hotel.domain.conver.MinHotelDeviceRenterVOConver;
import com.outmao.ebs.hotel.dto.GetHotelDeviceRenterListDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceRenterDTO;
import com.outmao.ebs.hotel.dto.HotelDeviceRenterLeaseDTO;
import com.outmao.ebs.hotel.entity.HotelDeviceRenter;
import com.outmao.ebs.hotel.entity.QHotelDeviceRenter;
import com.outmao.ebs.hotel.vo.HotelDeviceRenterVO;
import com.outmao.ebs.hotel.vo.MinHotelDeviceRenterVO;
import com.outmao.ebs.mall.merchant.common.annotation.SaveUserCommission;
import com.outmao.ebs.mall.merchant.common.annotation.SetUserCommission;
import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class HotelDeviceRenterDomainImpl extends BaseDomain implements HotelDeviceRenterDomain {

    @Autowired
    private HotelDeviceRenterDao hotelDeviceRenterDao;

    private HotelDeviceRenterVOConver hotelDeviceRenterVOConver=new HotelDeviceRenterVOConver();

    private MinHotelDeviceRenterVOConver minHotelDeviceRenterVOConver=new MinHotelDeviceRenterVOConver();

    @Transactional()
    @SaveUserCommission
    @Override
    public HotelDeviceRenter saveHotelDeviceRenter(HotelDeviceRenterDTO request) {
        HotelDeviceRenter renter=hotelDeviceRenterDao.findByUserIdLock(request.getUserId());
        if(renter==null){
            renter=new HotelDeviceRenter();
            renter.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(request,renter);
        renter.setKeyword(getKeyword(renter));
        renter.setUpdateTime(new Date());
        hotelDeviceRenterDao.save(renter);
        return renter;
    }

    @Transactional()
    @SaveUserCommission
    @Override
    public HotelDeviceRenter saveHotelDeviceRenterLease(HotelDeviceRenterLeaseDTO request) {
        HotelDeviceRenter renter=hotelDeviceRenterDao.findByUserIdLock(request.getUserId());
        if(renter==null){
            renter=new HotelDeviceRenter();
            renter.setCreateTime(new Date());
        }
        renter.setQuantity(renter.getQuantity()+request.getQuantity());
        renter.setAmount(renter.getAmount()+request.getAmount());
        BeanUtils.copyProperties(request,renter,"amount","quantity");
        renter.setKeyword(getKeyword(renter));
        renter.setUpdateTime(new Date());
        hotelDeviceRenterDao.save(renter);
        return renter;
    }



    public String getKeyword(HotelDeviceRenter data){
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

    @Override
    public HotelDeviceRenter getHotelDeviceRenterByUserId(Long userId) {
        return hotelDeviceRenterDao.findByUserId(userId);
    }

    @SetUserCommission
    @Override
    public HotelDeviceRenterVO getHotelDeviceRenterVOByUserId(Long userId) {
        QHotelDeviceRenter e=QHotelDeviceRenter.hotelDeviceRenter;
        HotelDeviceRenterVO vo=queryOne(e,e.userId.eq(userId),hotelDeviceRenterVOConver);
        return vo;
    }

    @SetUserCommission
    @Override
    public Page<HotelDeviceRenterVO> getHotelDeviceRenterVOPage(GetHotelDeviceRenterListDTO request, Pageable pageable) {
        QHotelDeviceRenter e=QHotelDeviceRenter.hotelDeviceRenter;
        Predicate p=null;
        if(!StringUtils.isEmpty(request.getKeyword())){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }
        return queryPage(e,p,hotelDeviceRenterVOConver,pageable);
    }

    @Override
    public List<MinHotelDeviceRenterVO> getMinHotelDeviceRenterVOListByUserIdIn(Collection<Long> userIdIn) {
        QHotelDeviceRenter e=QHotelDeviceRenter.hotelDeviceRenter;
        return queryList(e,e.userId.in(userIdIn),minHotelDeviceRenterVOConver);
    }


}
