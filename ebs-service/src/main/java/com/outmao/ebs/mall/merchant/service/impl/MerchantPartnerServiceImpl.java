package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.merchant.domain.MerchantPartnerDomain;
import com.outmao.ebs.mall.merchant.dto.DeleteMerchantPartnerDTO;
import com.outmao.ebs.mall.merchant.dto.GetMerchantPartnerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantPartnerDTO;
import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import com.outmao.ebs.mall.merchant.service.MerchantPartnerService;
import com.outmao.ebs.mall.merchant.service.MerchantService;
import com.outmao.ebs.mall.merchant.vo.MerchantPartnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantPartnerServiceImpl extends BaseService implements MerchantPartnerService {

    @Autowired
    private MerchantPartnerDomain merchantPartnerDomain;

    @Autowired
    private MerchantService merchantService;

    @Override
    public MerchantPartner saveMerchantPartner(MerchantPartnerDTO request) {
        if(request.getMerchantId()==null){
            Merchant merchant=merchantService.getMerchant();
            request.setMerchantId(merchant.getId());
        }
        return merchantPartnerDomain.saveMerchantPartner(request);
    }

    @Override
    public void deleteMerchantPartnerById(Long id) {
        merchantPartnerDomain.deleteMerchantPartnerById(id);
    }

    @Override
    public MerchantPartnerVO getMerchantPartnerVOById(Long id) {
        return merchantPartnerDomain.getMerchantPartnerVOById(id);
    }

    @Override
    public Page<MerchantPartnerVO> getMerchantPartnerVOPage(GetMerchantPartnerListDTO request, Pageable pageable) {
        return merchantPartnerDomain.getMerchantPartnerVOPage(request,pageable);
    }


    @Override
    public List<MerchantPartnerVO> getMerchantPartnerVOListByUserId(Long userId) {
        return merchantPartnerDomain.getMerchantPartnerVOListByUserId(userId);
    }


}
