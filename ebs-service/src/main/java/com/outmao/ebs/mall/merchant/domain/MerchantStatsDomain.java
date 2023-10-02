package com.outmao.ebs.mall.merchant.domain;

import com.outmao.ebs.mall.merchant.vo.MerchantStatsVO;

import java.util.Collection;
import java.util.List;

public interface MerchantStatsDomain {

    public MerchantStatsVO getMerchantStatsVOByMerchantId(Long merchantId);

    public List<MerchantStatsVO> getMerchantStatsVOListByMerchantIdIn(Collection<Long> merchantIdIn);


}
