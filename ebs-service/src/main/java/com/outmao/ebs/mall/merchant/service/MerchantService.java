package com.outmao.ebs.mall.merchant.service;

import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MerchantService {


    public Merchant saveMerchant(MerchantDTO request);

    public Merchant setMerchantStatus(SetMerchantStatusDTO request);

    public Merchant getMerchant();

    public Merchant getMerchantByOrgId(Long orgId);

    public Merchant getMerchantByUserId(Long userId);

    public MerchantVO getMerchantVOById(Long id);

    public MerchantVO getMerchantVOByUserId(Long userId);

    public MerchantVO getMerchantVOByShopId(Long shopId);

    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable);

    public List<SimpleMerchantVO> getSimpleMerchantVOListByIdIn(Collection<Long> idIn);



}
