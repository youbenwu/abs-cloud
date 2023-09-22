package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.GetUserLocationListDTO;
import com.outmao.ebs.user.dto.UserLocationDTO;
import com.outmao.ebs.user.entity.UserLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLocationService {

    // UserLocation
    /*
     *
     * 保存用户位置信息
     *
     * */
    public UserLocation saveUserLocation(UserLocationDTO request);


    /*
     *
     * 获取用户最后位置信息
     *
     * */
    public UserLocation getUserLocationByUserId(Long userId);


    /*
     *
     * 获取用户位置分页列表
     *
     * */
    public Page<UserLocation> getUserLocationPage(GetUserLocationListDTO request, Pageable pageable);


}
