package com.outmao.ebs.user.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.user.domain.OnlineDomain;
import com.outmao.ebs.user.entity.Online;
import com.outmao.ebs.user.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineServiceImpl extends BaseService implements OnlineService {

    @Autowired
    private OnlineDomain onlineDomain;



    @Override
    public Online saveOnline(Long userId, boolean message) {
        return onlineDomain.saveOnline(userId,message);
    }


    @Override
    public Online getOnlineByUserId(Long userId) {
        return onlineDomain.getOnlineByUserId(userId);
    }
}
