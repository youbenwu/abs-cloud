package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.AdvertChannelDao;
import com.outmao.ebs.portal.domain.AdvertChannelDomain;
import com.outmao.ebs.portal.dto.AdvertChannelDTO;
import com.outmao.ebs.portal.dto.GetAdvertChannelListDTO;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.entity.QAdvertChannel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Component
public class AdvertChannelDomainImpl extends BaseDomain implements AdvertChannelDomain {



    @Autowired
    private AdvertChannelDao advertChannelDao;


    @Transactional
    @Override
    public AdvertChannel saveAdvertChannel(AdvertChannelDTO request) {
        AdvertChannel c=request.getId()==null?null:advertChannelDao.getOne(request.getId());
        if(c==null){
            c=new AdvertChannel();
            c.setCreateTime(new Date());
            c.setOrgId(request.getOrgId());
        }

        BeanUtils.copyProperties(request,c,"orgId");

        advertChannelDao.save(c);

        return c;
    }

    @Transactional
    @Override
    public void deleteAdvertChannelById(Long id) {
        AdvertChannel c=advertChannelDao.getOne(id);
        advertChannelDao.delete(c);
    }

    @Override
    public AdvertChannel getAdvertChannelByCode(String code) {
        return advertChannelDao.findByCode(code);
    }

    @Override
    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable) {
        QAdvertChannel e=QAdvertChannel.advertChannel;

        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId());
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        if(p==null)
            return advertChannelDao.findAll(pageable);

        return advertChannelDao.findAll(p,pageable);
    }


}
