package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.user.common.constant.ClientType;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.user.dao.UserOauthDao;
import com.outmao.ebs.user.dao.UserOauthSessionDao;
import com.outmao.ebs.user.domain.UserOauthDomain;
import com.outmao.ebs.user.dto.SetAuthenticatedDTO;
import com.outmao.ebs.user.entity.UserOauth;
import com.outmao.ebs.user.entity.UserOauthSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
public class UserOauthDomainImpl extends BaseDomain implements UserOauthDomain {


    @Autowired
    private UserOauthDao userOauthDao;

    @Autowired
    private UserOauthSessionDao userOauthSessionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserOauth registerUserOauth(Long userId, String oauth, String principal, String credentials) {
        // 授权信息
        UserOauth auth = new UserOauth();
        auth.setUser(userDao.getOne(userId));
        auth.setOauth(oauth);
        auth.setPrincipal(principal);
        auth.setCredentials(credentials==null?null:passwordEncoder.encode(credentials));
        auth.setRegisterTime(new Date());
        userOauthDao.save(auth);
        return auth;
    }

    @Override
    public UserOauth getUserAuthByPrincipal(String principal) {
        return userOauthDao.findByPrincipal(principal);
    }


    @Transactional
    @Override
    public UserOauth updateCredentials(String principal, String credentials) {
        UserOauth auth =userOauthDao.findByPrincipal(principal);
        auth.setCredentials(credentials==null?null:passwordEncoder.encode(credentials));
        userOauthDao.save(auth);
        return auth;
    }

    @Transactional
    @Override
    public UserOauth updateCredentials(String principal, String credentials, String newCredentials) {
        UserOauth auth =userOauthDao.findByPrincipal(principal);

        if(!passwordEncoder.matches(credentials,auth.getCredentials())){
            throw new BusinessException("密码错误");
        }

        auth.setCredentials(credentials==null?null:passwordEncoder.encode(newCredentials));
        userOauthDao.save(auth);
        return auth;
    }


    @Override
    public UserOauthSession getUserOauthSessionByToken(String token) {
        return userOauthSessionDao.findByToken(token);
    }

    @Transactional
    @Override
    public UserOauthSession setAuthenticated(SetAuthenticatedDTO request) {
        if(request.getClientType()==null){
            request.setClientType(ClientType.PC_CLIENT.getName());
        }

        UserOauthSession session=userOauthSessionDao.findByUserIdAndClientType(request.getUserId(),request.getClientType());

        if(session==null){
            session=new UserOauthSession();
            session.setUser(userDao.getOne(request.getUserId()));
            session.setClientType(request.getClientType());
        }

        session.setOauth(userOauthDao.getOne(request.getOauthId()));
        session.setImei(request.getImei());
        session.setSessionKey(request.getSessionKey());
        session.setOauthTime(LocalDateTime.now());
        //7天后过期
        session.setExpireTime(request.getExpireTime()!=null?request.getExpireTime():session.getOauthTime().plusDays(7));
        //生成Token
        session.setToken(UUID.randomUUID().toString());

        userOauthSessionDao.save(session);

        return session;
    }

    @Transactional
    @Override
    public UserOauthSession setAuthenticatedNot(Long sessionId) {
        UserOauthSession session=userOauthSessionDao.getOne(sessionId);
        session.setToken(null);
        userOauthSessionDao.save(session);
        return session;
    }



}
