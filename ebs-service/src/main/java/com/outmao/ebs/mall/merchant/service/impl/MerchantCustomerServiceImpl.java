package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.mall.merchant.domain.MerchantCustomerDomain;
import com.outmao.ebs.mall.merchant.dto.GetMerchantCustomerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantCustomerDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import com.outmao.ebs.mall.merchant.service.MerchantCustomerService;
import com.outmao.ebs.mall.merchant.vo.MerchantCustomerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MerchantCustomerServiceImpl extends BaseService implements MerchantCustomerService {

    @Autowired
    private MerchantCustomerDomain merchantCustomerDomain;

    @Override
    public MerchantCustomer saveMerchantCustomer(MerchantCustomerDTO request) {
        return saveMerchantCustomer(request);
    }

    @Override
    public void deleteMerchantCustomerById(Long id) {
        merchantCustomerDomain.deleteMerchantCustomerById(id);
    }

    @Override
    public MerchantCustomer getMerchantCustomer(Long merchantId, Long userId) {
        return merchantCustomerDomain.getMerchantCustomer(merchantId,userId);
    }

    @Override
    public MerchantCustomerVO getMerchantCustomerVOById(Long id) {
        return merchantCustomerDomain.getMerchantCustomerVOById(id);
    }

    @Override
    public Page<MerchantCustomerVO> getMerchantCustomerVOPage(GetMerchantCustomerListDTO request, Pageable pageable) {
        return merchantCustomerDomain.getMerchantCustomerVOPage(request,pageable);
    }

    @Override
    public List<SimpleMerchantCustomerVO> getSimpleMerchantCustomerVOByIdIn(Collection<Long> idIn) {
        return merchantCustomerDomain.getSimpleMerchantCustomerVOByIdIn(idIn);
    }
}
