package com.outmao.ebs.user.dao;

import com.outmao.ebs.user.entity.Online;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnlineDao extends JpaRepository<Online,Long> {


    public Online findByUserId(Long userId);

}
