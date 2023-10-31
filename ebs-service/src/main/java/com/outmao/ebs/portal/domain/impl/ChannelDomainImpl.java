package com.outmao.ebs.portal.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.portal.dao.ChannelDao;
import com.outmao.ebs.portal.domain.ChannelDomain;
import com.outmao.ebs.portal.dto.ChannelDTO;
import com.outmao.ebs.portal.dto.GetChannelListDTO;
import com.outmao.ebs.portal.entity.Channel;
import com.outmao.ebs.portal.entity.QChannel;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Component
public class ChannelDomainImpl extends BaseDomain implements ChannelDomain {


    @Autowired
    private ChannelDao channelDao;

    @Transactional
    @Override
    public Channel saveChannel(ChannelDTO request) {
        Channel c=request.getId()==null?null:channelDao.getOne(request.getId());
        if(c==null){
            c=new Channel();
            c.setOrgId(request.getOrgId());
            c.setCreateTime(new Date());
        }
        c.setUpdateTime(new Date());
        BeanUtils.copyProperties(request,c,"orgId");

        security.hasPermission(c.getOrgId(),null);

        channelDao.save(c);
        return c;
    }

    @Transactional
    @Override
    public void deleteChannelById(Long id) {
        Channel c=channelDao.getOne(id);
        security.hasPermission(c.getOrgId(),null);
        channelDao.delete(c);
    }



    @Override
    public Channel getChannelByCode(String code) {
        return channelDao.findByCode(code);
    }

    @Override
    public Page<Channel> getChannelPage(GetChannelListDTO request, Pageable pageable) {

        QChannel e=QChannel.channel;

        Predicate p=null;

        if(request.getOrgId()!=null){
            p=e.orgId.eq(request.getOrgId());
        }

        if(request.getType()!=null){
            p=e.type.eq(request.getType()).and(p);
        }

        if(p==null)
           return channelDao.findAll(pageable);

        return channelDao.findAll(p,pageable);
    }


}
