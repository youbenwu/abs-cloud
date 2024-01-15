package com.outmao.ebs.mall.merchant.domain;


import com.outmao.ebs.mall.merchant.dto.GetMerchantListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantDTO;
import com.outmao.ebs.mall.merchant.dto.SetMerchantStatusDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.vo.MerchantVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface MerchantDomain {


    public Merchant saveMerchant(MerchantDTO request);

    public Merchant setMerchantStatus(SetMerchantStatusDTO request);

    public Merchant getMerchantByOrgId(Long orgId);

    public Merchant getMerchantByUserId(Long userId);

    public boolean existsByUserId(Long userId);

    public MerchantVO getMerchantVOById(Long id);

    public MerchantVO getMerchantVOByOrgId(Long orgId);

    public MerchantVO getMerchantVOByUserId(Long userId);

    public MerchantVO getMerchantVOByShopId(Long shopId);

    public Page<MerchantVO> getMerchantVOPage(GetMerchantListDTO request, Pageable pageable);

    public List<SimpleMerchantVO> getSimpleMerchantVOListByIdIn(Collection<Long> idIn);



}
