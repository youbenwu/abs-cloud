package com.outmao.ebs.user.common.data;

import com.outmao.ebs.user.dto.RegisterDTO;


public interface RegisterUser {

    public Long getUserId();

    public void setUserId(Long userId);

    public RegisterDTO getRegisterRequest();

}
