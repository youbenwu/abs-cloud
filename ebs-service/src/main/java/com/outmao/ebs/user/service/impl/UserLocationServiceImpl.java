package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserLocationDomain;
import com.outmao.ebs.user.dto.GetUserLocationListDTO;
import com.outmao.ebs.user.dto.UserLocationDTO;
import com.outmao.ebs.user.entity.UserLocation;
import com.outmao.ebs.user.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserLocationServiceImpl extends BaseService implements UserLocationService {

    @Autowired
    private UserLocationDomain userLocationDomain;

    @Override
    public UserLocation saveUserLocation(UserLocationDTO request) {

        return userLocationDomain.saveUserLocation(request);
    }

    @Override
    public UserLocation getUserLocationByUserId(Long userId) {

        return userLocationDomain.getUserLocationByUserId(userId);
    }

    @Override
    public Page<UserLocation> getUserLocationPage(GetUserLocationListDTO request, Pageable pageable) {
        return userLocationDomain.getUserLocationPage(request,pageable);
    }


}
