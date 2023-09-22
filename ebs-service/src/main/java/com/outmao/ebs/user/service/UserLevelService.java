package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.UserLevelDTO;
import com.outmao.ebs.user.entity.UserLevel;

import java.util.List;

public interface UserLevelService {

    // UserLevel
    public UserLevel saveUserLevel(UserLevelDTO request);

    public UserLevel getUserLevelByValue(int value);

    public List<UserLevel> getUserLevelList();


}
