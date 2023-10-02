package com.outmao.ebs.mall.merchant.domain;


import com.outmao.ebs.mall.merchant.vo.MerchantCustomerStatsVO;
import java.util.Collection;
import java.util.List;

public interface MerchantCustomerStatsDomain {

    public MerchantCustomerStatsVO getMerchantCustomerStatsVOByCustomerId(Long customerId);

    public List<MerchantCustomerStatsVO> getMerchantCustomerStatsVOListByCustomerIdIn(Collection<Long> customerIdIn);



}
