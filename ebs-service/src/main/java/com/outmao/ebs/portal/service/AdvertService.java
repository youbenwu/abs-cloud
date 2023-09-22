package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.AdvertDTO;
import com.outmao.ebs.portal.dto.GetAdvertListDTO;
import com.outmao.ebs.portal.dto.SetAdvertStatusDTO;
import com.outmao.ebs.portal.entity.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertService {


    public Advert saveAdvert(AdvertDTO request);

    public Advert setAdvertStatus(SetAdvertStatusDTO request);

    public void deleteAdvertById(Long id);

    public Page<Advert> getAdvertPage(GetAdvertListDTO request, Pageable pageable);


}
