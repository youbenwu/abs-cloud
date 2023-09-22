package com.outmao.ebs.sys.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.sys.domain.LogDomain;
import com.outmao.ebs.sys.dto.LogDTO;
import com.outmao.ebs.sys.entity.Log;
import com.outmao.ebs.sys.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl extends BaseService implements LogService {


    @Autowired
    private LogDomain logDomain;


    @Override
    public Log saveLog(LogDTO request) {
        return logDomain.saveLog(request);
    }

    @Override
    public Page<Log> getLogPage(Pageable pageable) {
        return logDomain.getLogPage(pageable);
    }

}
