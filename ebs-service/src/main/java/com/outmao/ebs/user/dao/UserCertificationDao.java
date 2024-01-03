package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.UserCertification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCertificationDao extends JpaRepository<UserCertification,Long> {

    public UserCertification findByUserId(Long userId);

}
