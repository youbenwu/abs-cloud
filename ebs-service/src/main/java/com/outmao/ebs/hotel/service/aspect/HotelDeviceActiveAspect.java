package com.outmao.ebs.hotel.service.aspect;


import com.outmao.ebs.hotel.dao.HotelDeviceDao;
import com.outmao.ebs.user.common.constant.UserActiveType;
import com.outmao.ebs.user.entity.UserActive;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class HotelDeviceActiveAspect {


    @Autowired
    private HotelDeviceDao hotelDeviceDao;


    @Pointcut("execution(public * com.outmao.ebs.user.domain.UserActiveDomain.saveUserActive(..))")
    public void saveUserActive() { }


    @Transactional()
    @AfterReturning(returning = "a", pointcut = "saveUserActive()")
    public void setOrderStatus(JoinPoint jp, UserActive a) {
        if(a.getUserId()==null)
            return;
        if(a.getType()== UserActiveType.QyHotelDeviceActive.getType()){
            hotelDeviceDao.active(a.getUserId());
        }else if(a.getType()== UserActiveType.QyHotelDeviceOn.getType()){
            hotelDeviceDao.activeOn(a.getUserId(),a.getCreateTime());
        }else if(a.getType()== UserActiveType.QyHotelDeviceOff.getType()){
            hotelDeviceDao.activeOff(a.getUserId(),a.getCreateTime());
        }
    }


}
