package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertUvLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertUvLogDao extends JpaRepository<AdvertUvLog,Long> {

    public AdvertUvLog findByKey(String key);

}
