package com.outmao.ebs.mall.product.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.product.domain.HouseCommunityDomain;
import com.outmao.ebs.mall.product.dto.GetHouseCommunityListDTO;
import com.outmao.ebs.mall.product.dto.HouseCommunityDTO;
import com.outmao.ebs.mall.product.entity.HouseCommunity;
import com.outmao.ebs.mall.product.service.HouseCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class HouseCommunityServiceImpl extends BaseService implements HouseCommunityService {


    @Autowired
    private HouseCommunityDomain houseCommunityDomain;

    @Override
    public HouseCommunity saveHouseCommunity(HouseCommunityDTO request) {
        return houseCommunityDomain.saveHouseCommunity(request);
    }

    @Override
    public void deleteHouseCommunityById(Long id) {
       houseCommunityDomain.deleteHouseCommunityById(id);
    }

    @Override
    public HouseCommunity getHouseCommunityById(Long id) {
        return houseCommunityDomain.getHouseCommunityById(id);
    }

    @Override
    public Page<HouseCommunity> getHouseCommunityPage(GetHouseCommunityListDTO request, Pageable pageable) {
        return houseCommunityDomain.getHouseCommunityPage(request,pageable);
    }
}
