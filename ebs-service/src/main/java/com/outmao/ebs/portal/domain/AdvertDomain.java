package com.outmao.ebs.portal.domain;

import com.outmao.ebs.portal.dto.AdvertDTO;
import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.dto.SetAdvertOrderStatusDTO;
import com.outmao.ebs.portal.dto.SetAdvertStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertDomain {

    public Advert saveAdvert(AdvertDTO request);

    public Advert setAdvertStatus(SetAdvertStatusDTO request);

    public void deleteAdvertById(Long id);

    public void setAdvertSort(Long id, int sort);

    public Advert buyPv(Long id,long buyPv,double buyAmount);

    public Advert pv(Long id);

    public List<Advert> getAdvertList();

    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable);

    public AdvertOrder saveAdvertOrder(AdvertOrder request);

    public AdvertOrder setAdvertOrderStatus(SetAdvertOrderStatusDTO request);



}
