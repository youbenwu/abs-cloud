package com.outmao.ebs.sys.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.sys.domain.AppDomain;
import com.outmao.ebs.sys.dto.AppDTO;
import com.outmao.ebs.sys.entity.App;
import com.outmao.ebs.sys.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl extends BaseService implements AppService {

    @Autowired
    private AppDomain appDomain;

    @Override
    public App saveApp(AppDTO request) {
        return appDomain.saveApp(request);
    }

    @Override
    public App getAppByCode(String code) {
        return appDomain.getAppByCode(code);
    }
}
