package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertDTO;
import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.dto.SetAdvertOrderStatusDTO;
import com.outmao.ebs.portal.dto.SetAdvertStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertDomain {

    public Advert saveAdvert(AdvertDTO request);

    public Advert setAdvertStatus(SetAdvertStatusDTO request);

    public void deleteAdvertById(Long id);

    public Advert buyPv(Long id,long buyPv,double buyAmount);

    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable);



    public AdvertOrder saveAdvertOrder(AdvertOrder request);

    public AdvertOrder setAdvertOrderStatus(SetAdvertOrderStatusDTO request);



}
