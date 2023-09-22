package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.UserAddressDomain;
import com.outmao.ebs.user.dto.GetUserAddressListDTO;
import com.outmao.ebs.user.dto.UserAddressDTO;
import com.outmao.ebs.user.entity.UserAddress;
import com.outmao.ebs.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserAddressServiceImpl extends BaseService implements UserAddressService {

    @Autowired
    private UserAddressDomain userAddressDomain;

    @Override
    public UserAddress saveUserAddres(UserAddressDTO request) {
        return userAddressDomain.saveUserAddres(request);
    }

    @Override
    public void deleteUserAddressById(Long id) {
        userAddressDomain.deleteUserAddressById(id);
    }

    @Override
    public void deleteUserAddressByIdIn(List<Long> idIn) {
        userAddressDomain.deleteUserAddressByIdIn(idIn);
    }

    @Override
    public UserAddress getUserAddressById(Long id) {
        return userAddressDomain.getUserAddressById(id);
    }

    @Override
    public UserAddress getDefaultUserAddressByUserId(Long userId) {
        return userAddressDomain.getDefaultUserAddressByUserId(userId);
    }

    @Override
    public Page<UserAddress> getUserAddressPage(GetUserAddressListDTO request, Pageable pageable) {
        return userAddressDomain.getUserAddressPage(request,pageable);
    }
}
