package com.outmao.ebs.thirdpartys.rongcloud.service;

import com.outmao.ebs.thirdpartys.rongcloud.dto.RcGroupDTO;
import com.outmao.ebs.thirdpartys.rongcloud.dto.RcRegisterUserDTO;
import com.outmao.ebs.thirdpartys.rongcloud.entity.RcGroup;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.models.user.UserModel;

public interface RongcloudService {

    public Token registerUser(RcRegisterUserDTO request);


    public RcGroup saveGroup(RcGroupDTO request);




}
