package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.DataItemGetter;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.portal.domain.RecommendDomain;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantDomain;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerDomain;
import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class MerchantServiceImpl extends BaseService implements MerchantService {

    @Autowired
    private MerchantDomain merchantDomain;


    @Override
    public Merchant saveMerchant(MerchantDTO request) {

        return merchantDomain.saveMerchant(request);
    }

    @Override
    public Merchant setMerchantStatus(SetMerchantStatusDTO request) {
        return merchantDomain.setMerchantStatus(request);
    }

    @Override
    public MerchantVO getMerchantVOById(Long id) {
        return merchantDomain.getMerchantVOById(id);
    }

    @Override
    public MerchantVO getMerchantVOByShopId(Long shopId) {
        return merchantDomain.getMerchantVOByShopId(shopId);
    }

    @Override
    public MerchantVO getMerchantVOByUserId(Long userId) {
        return merchantDomain.getMerchantVOByUserId(userId);
    }

    @Override
    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable) {
        return merchantDomain.getMerchantVOPage(request,pageable);
    }





    @Override
    public List<SimpleMerchantVO> getSimpleMerchantVOListByIdIn(Collection<Long> idIn) {
        return merchantDomain.getSimpleMerchantVOListByIdIn(idIn);
    }






}
