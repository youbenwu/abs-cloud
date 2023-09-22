package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.ChannelDTO;
import com.outmao.ebs.portal.dto.GetChannelListDTO;
import com.outmao.ebs.portal.entity.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChannelDomain {

    public Channel saveChannel(ChannelDTO request);

    public void deleteChannelById(Long id);

    public Page<Channel> getChannelPage(GetChannelListDTO request, Pageable pageable);


}
