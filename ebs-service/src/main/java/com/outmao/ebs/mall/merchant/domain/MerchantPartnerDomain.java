package com.outmao.ebs.mall.merchant.domain;


import com.outmao.ebs.mall.merchant.dto.GetMerchantPartnerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantPartnerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MerchantPartnerDomain {

    public MerchantPartner saveMerchantPartner(MerchantPartnerDTO request);

    public void deleteMerchantPartnerById(Long id);

    public List<MerchantPartner> getMerchantPartnerList();

    public MerchantPartnerVO getMerchantPartnerVOById(Long id);

    public List<MerchantPartnerVO> getMerchantPartnerVOListByUserId(Long userId);

    public Page<MerchantPartnerVO> getMerchantPartnerVOPage(GetMerchantPartnerListDTO request, Pageable pageable);



}
