package com.outmao.ebs.user.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.user.dao.OnlineDao;
import com.outmao.ebs.user.domain.OnlineDomain;
import com.outmao.ebs.user.entity.Online;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class OnlineDomainImpl extends BaseDomain implements OnlineDomain {

    @Autowired
    private OnlineDao onlineDao;


    @CacheEvict(value = "cache_online", key = "#userId")
    @Transactional
    @Override
    public Online saveOnline(Long userId, boolean message) {
        Online online=onlineDao.findByUserId(userId);
        if(online==null){
            online=new Online();
        }
        online.setMessage(message);
        online.setTime(new Date().getTime());
        onlineDao.save(online);
        return online;
    }


    @Cacheable(value = "cache_online", key = "#userId")
    @Override
    public Online getOnlineByUserId(Long userId) {
        return onlineDao.findByUserId(userId);
    }


}
