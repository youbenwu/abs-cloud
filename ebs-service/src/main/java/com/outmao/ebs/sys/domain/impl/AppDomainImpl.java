package com.outmao.ebs.sys.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.sys.dao.AppDao;
import com.outmao.ebs.sys.domain.AppDomain;
import com.outmao.ebs.sys.dto.AppDTO;
import com.outmao.ebs.sys.entity.App;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class AppDomainImpl extends BaseDomain implements AppDomain {

    @Autowired
    private AppDao appDao;


    @Transactional
    @Override
    public App saveApp(AppDTO request) {

        App app=request.getId()==null?null:appDao.getOne(request.getId());
        if(app==null){
            app=new App();
            app.setCreateTime(new Date());

        }

        BeanUtils.copyProperties(request,app);
        app.setUpdateTime(new Date());
        appDao.save(app);
        return app;
    }

    @Override
    public App getAppByCode(String code) {
        return appDao.findByCode(code);
    }
}
