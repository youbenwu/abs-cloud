package com.outmao.ebs.mall.merchant.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.vo.DataItemGetter;
import com.outmao.ebs.mall.merchant.domain.MerchantBrokerDomain;
import com.outmao.ebs.mall.merchant.dto.GetMerchantBrokerForServiceDTO;
import com.outmao.ebs.mall.merchant.dto.GetMerchantBrokerListDTO;
import com.outmao.ebs.mall.merchant.dto.MerchantBrokerDTO;
import com.outmao.ebs.mall.merchant.dto.SetMerchantBrokerStatusDTO;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.service.MerchantBrokerService;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import com.outmao.ebs.portal.domain.RecommendDomain;
import com.outmao.ebs.portal.dto.GetRecommendListDTO;
import com.outmao.ebs.portal.vo.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class MerchantBrokerServiceImpl extends BaseService implements MerchantBrokerService {

    @Autowired
    private MerchantBrokerDomain merchantBrokerDomain;


    @Autowired
    private RecommendDomain recommendDomain;


    @Override
    public MerchantBroker saveMerchantBroker(MerchantBrokerDTO request) {
        return merchantBrokerDomain.saveMerchantBroker(request);
    }

    @Override
    public MerchantBroker getMerchantBrokerById(Long id) {
        return merchantBrokerDomain.getMerchantBrokerById(id);
    }

    @Override
    public void deleteMerchantBrokerById(Long id) {
        merchantBrokerDomain.deleteMerchantBrokerById(id);
    }

    @Override
    public MerchantBroker setMerchantBrokerStatus(SetMerchantBrokerStatusDTO request) {
        return merchantBrokerDomain.setMerchantBrokerStatus(request);
    }

    @Override
    public MerchantBrokerVO getMerchantBrokerVOById(Long id) {
        return merchantBrokerDomain.getMerchantBrokerVOById(id);
    }

    @Override
    public Page<MerchantBrokerVO> getMerchantBrokerVOPage(GetMerchantBrokerListDTO request, Pageable pageable) {
        return merchantBrokerDomain.getMerchantBrokerVOPage(request,pageable);
    }

    @Override
    public List<SimpleMerchantBrokerVO> getSimpleMerchantBrokerVOListByIdIn(Collection<Long> idIn) {
        return merchantBrokerDomain.getSimpleMerchantBrokerVOListByIdIn(idIn);
    }

    @Override
    public List<MerchantBrokerVO> getMerchantBrokerVOListByCustomerUserId(Long customerUserId) {
        return merchantBrokerDomain.getMerchantBrokerVOListByCustomerUserId(customerUserId);
    }

    @Override
    public List<MerchantBrokerVO> getMerchantBrokerVOListByUserId(Long userId) {
        return merchantBrokerDomain.getMerchantBrokerVOListByUserId(userId);
    }

    @Override
    public MerchantBrokerVO getMerchantBrokerVOForService(GetMerchantBrokerForServiceDTO request) {
        return merchantBrokerDomain.getMerchantBrokerVOForService(request);
    }

    @Override
    public Page<RecommendVO<SimpleMerchantBrokerVO>> getMerchantMemberRecommendVOPage(GetRecommendListDTO request, Pageable pageable) {
        return recommendDomain.getRecommendVOPage(request,new DataItemGetter<SimpleMerchantBrokerVO>() {
            @Override
            public List<SimpleMerchantBrokerVO> getDataItemListByIdIn(Collection<Long> idIn) {
                return merchantBrokerDomain.getSimpleMerchantBrokerVOListByIdIn(idIn);
            }
        },pageable);
    }


}
