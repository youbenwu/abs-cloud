package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.portal.domain.ChannelDomain;
import com.outmao.ebs.portal.dto.ChannelDTO;
import com.outmao.ebs.portal.dto.GetChannelListDTO;
import com.outmao.ebs.portal.entity.Channel;
import com.outmao.ebs.portal.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl extends BaseService implements ChannelService {

    @Autowired
    private ChannelDomain channelDomain;

    @Override
    public Channel saveChannel(ChannelDTO request) {
        return channelDomain.saveChannel(request);
    }

    @Override
    public void deleteChannelById(Long id) {
        channelDomain.deleteChannelById(id);
    }

    @Override
    public Page<Channel> getChannelPage(GetChannelListDTO request, Pageable pageable) {
        return channelDomain.getChannelPage(request,pageable);
    }

}
