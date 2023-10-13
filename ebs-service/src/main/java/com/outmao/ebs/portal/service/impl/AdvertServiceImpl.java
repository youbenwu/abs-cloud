package com.outmao.ebs.portal.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.portal.domain.AdvertDomain;
import com.outmao.ebs.portal.dto.AdvertDTO;
import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.dto.SetAdvertStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertChannel;
import com.outmao.ebs.portal.service.AdvertChannelService;
import com.outmao.ebs.portal.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class AdvertServiceImpl extends BaseService implements AdvertService {

    @Autowired
    private AdvertDomain advertDomain;

    @Autowired
    private AdvertChannelService advertChannelService;


    @Override
    public Advert saveAdvert(AdvertDTO request) {
        return advertDomain.saveAdvert(request);
    }

    @Override
    public Advert setAdvertStatus(SetAdvertStatusDTO request) {
        return advertDomain.setAdvertStatus(request);
    }

    @Override
    public void deleteAdvertById(Long id) {

        advertDomain.deleteAdvertById(id);
    }

    @Override
    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable) {
        if(!StringUtils.isEmpty(request.getChannelCode())){
          AdvertChannel channel= advertChannelService.getAdvertChannelByCode(request.getChannelCode());
          if(channel!=null){
              request.setChannelId(channel.getId());
          }else{
              throw new BusinessException("广告频道不存在");
          }
        }
        return advertDomain.getAdvertPage(request,pageable);
    }


    @Override
    public List<Advert> getAdvertList(String channelCode, int size) {
        GetAdvertListDTO dto=new GetAdvertListDTO();
        dto.setChannelCode(channelCode);
        dto.setStatus(1);
        Page<Advert> page=getAdvertPage(dto, PageRequest.of(0,size));
        return page.getContent();
    }


}
