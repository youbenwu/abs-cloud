package com.outmao.ebs.mall.merchant.domain;

import com.outmao.ebs.mall.merchant.vo.MerchantBrokerStatsVO;

import java.util.Collection;
import java.util.List;

public interface MerchantBrokerStatsDomain {

    public MerchantBrokerStatsVO getMerchantBrokerStatsVOBybrokerId(Long brokerId);

    public List<MerchantBrokerStatsVO> getMerchantBrokerStatsVOListByBrokerIdIn(Collection<Long> brokerIdIn);



}
