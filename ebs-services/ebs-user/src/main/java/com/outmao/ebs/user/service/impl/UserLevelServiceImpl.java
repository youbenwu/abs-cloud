package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserLevelDomain;
import com.outmao.ebs.user.dto.UserLevelDTO;
import com.outmao.ebs.user.entity.UserLevel;
import com.outmao.ebs.user.service.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserLevelServiceImpl extends BaseService implements UserLevelService {


    @Autowired
    private UserLevelDomain userLevelDomain;

    @Override
    public UserLevel saveUserLevel(UserLevelDTO request) {
        return userLevelDomain.saveUserLevel(request);
    }

    @Override
    public List<UserLevel> getUserLevelList() {
        return userLevelDomain.getUserLevelList();
    }

    @Override
    public UserLevel getUserLevelByValue(int value) {
        return userLevelDomain.getUserLevelByValue(value);
    }


}
