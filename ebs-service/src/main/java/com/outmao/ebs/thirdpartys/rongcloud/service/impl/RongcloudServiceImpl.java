package com.outmao.ebs.thirdpartys.rongcloud.service.impl;

import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.thirdpartys.rongcloud.service.RongcloudService;
import com.outmao.ebs.thirdpartys.rongcloud.vo.Token;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RongcloudServiceImpl implements RongcloudService {

    @Autowired
    private RongCloud rongCloud;


    @Override
    public Token registerUser(UserModel request) {
        User user = rongCloud.user;
        try {
            TokenResult result=user.register(request);
            if(result.code==200){
                Token token=new Token();
                BeanUtils.copyProperties(result,token);
                return token;
            }else{
                log.error("注册融云用户出错",result.errorMessage);
                throw new BusinessException("注册融云用户出错");
            }
        }catch (Exception e){
            log.error("注册融云用户出错",e);
            throw new BusinessException("注册融云用户出错");
        }

    }


}
