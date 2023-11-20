package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.domain.AdvertPvLogDomain;
import com.outmao.ebs.portal.dto.SaveAdvertPvLogListDTO;
import com.outmao.ebs.portal.entity.AdvertPvLog;
import com.outmao.ebs.portal.service.AdvertPvLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdvertPvLogServiceImpl extends BaseService implements AdvertPvLogService {

    @Autowired
    private AdvertPvLogDomain advertPvLogDomain;


    @Override
    public AdvertPvLog saveAdvertPvLog(AdvertPvLog request) {
        AdvertPvLog log= advertPvLogDomain.saveAdvertPvLog(request);
        return log;
    }


    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogList(SaveAdvertPvLogListDTO request) {
        List<AdvertPvLog> list=new ArrayList<>(request.getAdverts().size());
        request.getAdverts().forEach(t->{
            AdvertPvLog log=new AdvertPvLog();
            log.setAdvertId(t);
            log.setUserId(request.getUserId());
            saveAdvertPvLog(log);
            list.add(log);
        });
        return list;
    }

    @Async
    @Transactional
    @Override
    public List<AdvertPvLog> saveAdvertPvLogListAsync(SaveAdvertPvLogListDTO request) {
        return saveAdvertPvLogList(request);
    }


}
