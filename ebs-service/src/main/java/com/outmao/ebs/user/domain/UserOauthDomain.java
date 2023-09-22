package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;

public interface UserOauthDomain {


    public UserOauth registerUserOauth(Long userId, String oauth, String principal, String credentials);

    public UserOauth getUserAuthByPrincipal(String principal);

    public UserOauth updateCredentials(String principal, String credentials);

    public UserOauth updateCredentials(String principal, String credentials, String newCredentials);

    /**
     *
     * 获取授权状态
     *
     *
     **/
    public UserOauthSession getUserOauthSessionByToken(String token);
    /**
     *
     * 设置授权成功状态
     *
     *
     **/
    public UserOauthSession setAuthenticated(SetAuthenticatedDTO request);

    /**
     *
     * 设置非授权状态
     *
     *
     **/
    public UserOauthSession setAuthenticatedNot(Long sessionId);


}
