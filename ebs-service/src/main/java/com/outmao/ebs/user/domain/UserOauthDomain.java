package com.outmao.ebs.user.domain;

import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;

import java.util.List;

public interface UserOauthDomain {


    public UserOauth registerUserOauth(Long userId, String oauth, String principal, String credentials);

    public UserOauth getUserAuthByPrincipal(String principal);

    public UserOauth updateCredentials(String principal, String credentials);

    public UserOauth updateCredentials(String principal, String credentials, String newCredentials);

    public List<UserOauth> getUserOauth(Long userId, Oauth oauth);

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
