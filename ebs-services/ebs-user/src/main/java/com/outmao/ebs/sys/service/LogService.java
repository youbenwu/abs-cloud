package com.outmao.ebs.sys.service;

import com.outmao.ebs.sys.dto.LogDTO;
import com.outmao.ebs.sys.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LogService {


    public Log saveLog(LogDTO request);


    public Page<Log> getLogPage(Pageable pageable);



}
