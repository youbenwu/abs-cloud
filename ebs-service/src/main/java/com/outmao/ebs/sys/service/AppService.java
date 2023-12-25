package com.outmao.ebs.sys.service;

import com.outmao.ebs.sys.dto.AppDTO;
import com.outmao.ebs.sys.entity.App;

public interface AppService {


    public App saveApp(AppDTO request);

    public App getAppByCode(String code);

}
