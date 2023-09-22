package com.outmao.ebs.user.service;

import com.outmao.ebs.user.dto.GetUserDataListDTO;
import com.outmao.ebs.user.dto.UserDataDTO;
import com.outmao.ebs.user.entity.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDataService {


    public UserData saveUserData(UserDataDTO request);

    public UserData getUserData(Long userId, String name);

    public List<UserData> getUserDataList(GetUserDataListDTO request);

    public Page<UserData> getUserDataPage(GetUserDataListDTO request, Pageable pageable);


}
