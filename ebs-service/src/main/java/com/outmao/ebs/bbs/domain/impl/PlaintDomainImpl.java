package com.outmao.ebs.bbs.domain.impl;


import com.outmao.ebs.bbs.dao.PlaintDao;
import com.outmao.ebs.bbs.domain.PlaintDomain;
import com.outmao.ebs.bbs.dto.plaint.GetPlaintListDTO;
import com.outmao.ebs.bbs.dto.plaint.PlaintDTO;
import com.outmao.ebs.bbs.entity.Plaint;
import com.outmao.ebs.common.base.BaseDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class PlaintDomainImpl extends BaseDomain implements PlaintDomain {

    @Autowired
    PlaintDao plaintDao;

    @Transactional
    @Override
    public Plaint savePlaint(PlaintDTO request) {

        Plaint c=new Plaint();
        BeanUtils.copyProperties(request,c);
        c.setCreateTime(new Date());
        c.setUpdateTime(new Date());
        c.setStatus(0);
        c.setStatusRemark("未处理");
        plaintDao.save(c);

        return c;
    }


    @Transactional
    @Override
    public Plaint setPlaintStatus(Long id, int status, String statusRemark) {
        Plaint c= plaintDao.getOne(id);
        c.setStatus(status);
        c.setStatusRemark(statusRemark);
        c.setUpdateTime(new Date());
        plaintDao.save(c);
        return c;
    }

    @Override
    public Page<Plaint> getPlaintPage(GetPlaintListDTO request, Pageable pageable) {
        if(request.getStatusIn()==null)
            return plaintDao.findAll(pageable);
        return plaintDao.findAllByStatusIn(request.getStatusIn(),pageable);
    }


}
