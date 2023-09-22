package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserOauthSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthSessionDao extends JpaRepository<UserOauthSession,Long> {

    public UserOauthSession findByToken(String token);

    public UserOauthSession findByUserIdAndClientType(Long userId, String clientType);

}
