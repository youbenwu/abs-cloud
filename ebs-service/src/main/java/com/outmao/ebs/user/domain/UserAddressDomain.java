package com.outmao.ebs.user.domain;


import com.outmao.ebs.user.dto.GetUserAddressListDTO;
import com.outmao.ebs.user.dto.UserAddressDTO;
import com.outmao.ebs.user.entity.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserAddressDomain {

    public UserAddress saveUserAddres(UserAddressDTO request);

    public void deleteUserAddressById(Long id);

    public void deleteUserAddressByIdIn(List<Long> idIn);

    public UserAddress getUserAddressById(Long id);

    public UserAddress getDefaultUserAddressByUserId(Long userId);

    public Page<UserAddress> getUserAddressPage(GetUserAddressListDTO request, Pageable pageable);

}
