package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserDataDomain;
import com.outmao.ebs.user.dto.GetUserDataListDTO;
import com.outmao.ebs.user.dto.UserDataDTO;
import com.outmao.ebs.user.entity.UserData;
import com.outmao.ebs.user.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataServiceImpl extends BaseService implements UserDataService {

    @Autowired
    private UserDataDomain userDataDomain;

    @Override
    public UserData saveUserData(UserDataDTO request) {
        return userDataDomain.saveUserData(request);
    }


    @Override
    public UserData getUserData(Long userId, String name) {
        return userDataDomain.getUserData(userId,name);
    }

    @Override
    public List<UserData> getUserDataList(GetUserDataListDTO request) {
        return userDataDomain.getUserDataList(request);
    }

    @Override
    public Page<UserData> getUserDataPage(GetUserDataListDTO request, Pageable pageable) {
        return userDataDomain.getUserDataPage(request,pageable);
    }


}
