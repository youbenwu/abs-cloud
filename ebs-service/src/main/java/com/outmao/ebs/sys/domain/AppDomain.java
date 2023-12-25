package com.outmao.ebs.sys.domain;

import com.outmao.ebs.sys.dto.AppDTO;
import com.outmao.ebs.sys.entity.App;

public interface AppDomain {


    public App saveApp(AppDTO request);

    public App getAppByCode(String code);

}
