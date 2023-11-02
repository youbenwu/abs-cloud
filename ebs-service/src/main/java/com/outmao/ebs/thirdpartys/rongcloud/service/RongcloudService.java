package com.outmao.ebs.thirdpartys.rongcloud.service;

import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.models.user.UserModel;

public interface RongcloudService {

    public Token registerUser(UserModel request);



}
