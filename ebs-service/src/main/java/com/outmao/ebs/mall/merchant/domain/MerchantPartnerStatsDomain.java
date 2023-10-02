package com.outmao.ebs.mall.merchant.domain;

import com.outmao.ebs.mall.merchant.vo.MerchantPartnerStatsVO;

import java.util.Collection;
import java.util.List;

public interface MerchantPartnerStatsDomain {

    public MerchantPartnerStatsVO getMerchantPartnerStatsVOByPartnerId(Long partnerId);

    public List<MerchantPartnerStatsVO> getMerchantPartnerStatsVOByPartnerIdIn(Collection<Long> partnerIdIn);


}
