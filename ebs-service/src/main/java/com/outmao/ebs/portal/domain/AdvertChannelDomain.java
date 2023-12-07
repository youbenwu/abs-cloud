package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertChannelDTO;
import com.outmao.ebs.portal.dto.GetAdvertChannelListDTO;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.vo.AdvertChannelVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertChannelDomain {

    public AdvertChannel saveAdvertChannel(AdvertChannelDTO request);

    public void deleteAdvertChannelById(Long id);

    public AdvertChannel getAdvertChannelById(Long id);

    public AdvertChannel getAdvertChannelByCode(String code);

    public List<AdvertChannel> getAdvertChannelList(GetAdvertChannelListDTO request);

    public Page<AdvertChannel> getAdvertChannelPage(GetAdvertChannelListDTO request, Pageable pageable);

    public AdvertChannelVO getAdvertChannelVOById(Long id);

    public AdvertChannelVO getAdvertChannelVOByCode(String code);

    public List<AdvertChannelVO> getAdvertChannelVOList(GetAdvertChannelListDTO request);

    public Page<AdvertChannelVO> getAdvertChannelVOPage(GetAdvertChannelListDTO request, Pageable pageable);



}
