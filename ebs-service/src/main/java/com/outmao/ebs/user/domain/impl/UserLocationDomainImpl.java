package com.outmao.ebs.user.domain.impl;



import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dao.UserLocationDao;
import com.outmao.ebs.user.domain.UserLocationDomain;
import com.outmao.ebs.user.dto.GetUserLocationListDTO;
import com.outmao.ebs.user.dto.UserLocationDTO;
import com.outmao.ebs.user.entity.QUserLocation;
import com.outmao.ebs.user.entity.UserLocation;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class UserLocationDomainImpl extends BaseDomain implements UserLocationDomain {

    @Autowired
    private UserLocationDao userLocationDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public UserLocation saveUserLocation(UserLocationDTO request) {
        UserLocation loc = new UserLocation();
        loc.setUser(userDao.getOne(request.getUserId()));
        BeanUtils.copyProperties(request,loc);
        loc.setCreateTime(new Date());
        userLocationDao.save(loc);
        return loc;
    }

    @Override
    public UserLocation getUserLocationByUserId(Long userId) {
        QUserLocation e=QUserLocation.userLocation;
        return QF.select(e).from(e).where(e.user.id.eq(userId)).orderBy(e.id.desc()).offset(0).limit(1).fetchOne();
    }

    @Override
    public Page<UserLocation> getUserLocationPage(GetUserLocationListDTO request, Pageable pageable) {
        QUserLocation e=QUserLocation.userLocation;
        Predicate p=e.user.id.eq(request.getUserId());
        if(request.getStartTime()!=null){
            p=e.createTime.after(request.getStartTime()).and(p);
        }
        if(request.getEndTime()!=null){
            p=e.createTime.before(request.getEndTime()).and(p);
        }
        return userLocationDao.findAll(p,pageable);
    }


}
