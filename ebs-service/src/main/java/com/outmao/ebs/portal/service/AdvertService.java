package com.outmao.ebs.portal.service;

import com.outmao.ebs.portal.dto.*;
import com.outmao.ebs.portal.entity.Advert;
import com.outmao.ebs.portal.entity.AdvertBuy;
import com.outmao.ebs.portal.entity.AdvertBuyDisplay;
import com.outmao.ebs.portal.vo.AdvertVO;
import com.outmao.ebs.portal.vo.QyStatsAdvertByHotelVO;
import com.outmao.ebs.portal.vo.StatsAdvertStatusVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface AdvertService {


    public Advert saveAdvert(AdvertDTO request);

    public Advert setAdvertDisplay(SetAdvertDisplayDTO request);

    public Advert setAdvertStatus(SetAdvertStatusDTO request);

    public void deleteAdvertById(Long id);

    public Advert buy(Long id, AdvertBuy buy);

    public Advert buyDisplay(Long id, AdvertBuyDisplay buyDisplay);

    public void pv(AdvertPvDTO request);

    public AdvertVO getAdvertVOById(Long id);

    public List<AdvertVO> getAdvertVOList(GetAdvertListDTO request);

    public List<AdvertVO> getAdvertVOListByIdIn(Collection<Long> idIn);

    public Page<AdvertVO> getAdvertVOPage(GetAdvertListDTO request, Pageable pageable);


    public List<AdvertVO> getAdvertVOList(GetAdvertListForHotelPadDTO request);

    public Page<AdvertVO> getAdvertVOPage(GetAdvertListForHotelPadDTO request, Pageable pageable);

    public List<StatsAdvertStatusVO> getStatsAdvertStatusVOList(GetAdvertListDTO request);



}
