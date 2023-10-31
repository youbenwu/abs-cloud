package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertService {


    public Advert saveAdvert(AdvertDTO request);

    public Advert setAdvertStatus(SetAdvertStatusDTO request);

    public void deleteAdvertById(Long id);

    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable);


    public List<Advert> getAdvertList(String channelCode,int size);


    public AdvertOrder saveAdvertOrder(AdvertOrderDTO request);


    public AdvertOrder setAdvertOrderStatus(SetAdvertOrderStatusDTO request);


}
