package com.outmao.ebs.mall.merchant.domain;

import com.outmao.ebs.mall.merchant.dto.*;
import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import com.outmao.ebs.mall.merchant.vo.MerchantBrokerVO;
import com.outmao.ebs.mall.merchant.vo.SimpleMerchantBrokerVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

public interface MerchantBrokerDomain {

    public MerchantBroker saveMerchantBroker(MerchantBrokerDTO request);

    public MerchantBroker getMerchantBrokerById(Long id);

    public void deleteMerchantBrokerById(Long id);

    public MerchantBroker setMerchantBrokerStatus(SetMerchantBrokerStatusDTO request);

    public MerchantBrokerVO getMerchantBrokerVOById(Long id);

    public Page<MerchantBrokerVO> getMerchantBrokerVOPage(GetMerchantBrokerListDTO request, Pageable pageable);

    public List<SimpleMerchantBrokerVO> getSimpleMerchantBrokerVOListByIdIn(Collection<Long> idIn);

    public List<MerchantBrokerVO> getMerchantBrokerVOListByCustomerUserId(Long customerUserId);

    public List<MerchantBrokerVO> getMerchantBrokerVOListByUserId(Long userId);

    //为商品获取一个经纪人服务
    public MerchantBrokerVO getMerchantBrokerVOForService(GetMerchantBrokerForServiceDTO request);

}
