package com.outmao.ebs.sys.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.sys.dao.LogDao;
import com.outmao.ebs.sys.domain.LogDomain;
import com.outmao.ebs.sys.dto.LogDTO;
import com.outmao.ebs.sys.entity.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogDomainImpl extends BaseDomain implements LogDomain {

    @Autowired
    private LogDao logDao;


    @Transactional
    @Override
    public Log saveLog(LogDTO request) {
        Log log=new Log();
        BeanUtils.copyProperties(request,log);
        logDao.save(log);
        return log;
    }

    @Override
    public Page<Log> getLogPage(Pageable pageable) {
        return logDao.findAll(pageable);
    }
}
