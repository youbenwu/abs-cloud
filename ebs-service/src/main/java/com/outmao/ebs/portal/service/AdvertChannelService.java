package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.AdvertChannelDTO;
import com.outmao.ebs.portal.dto.GetAdvertChannelListDTO;
import com.outmao.ebs.portal.entity.AdvertChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertChannelService {

    public AdvertChannel saveAdvertChannel(AdvertChannelDTO request);

    public void deleteAdvertChannelById(Long id);

    public AdvertChannel getAdvertChannelByCode(String code);

    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable);


}
