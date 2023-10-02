package com.outmao.ebs.mall.product.domain;

import com.outmao.ebs.mall.product.dto.GetHouseCommunityListDTO;
import com.outmao.ebs.mall.product.dto.HouseCommunityDTO;
import com.outmao.ebs.mall.product.entity.HouseCommunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseCommunityDomain {


    public HouseCommunity saveHouseCommunity(HouseCommunityDTO request);

    public void deleteHouseCommunityById(Long id);

    public HouseCommunity getHouseCommunityById(Long id);

    public Page<HouseCommunity> getHouseCommunityPage(GetHouseCommunityListDTO request, Pageable pageable);


}
