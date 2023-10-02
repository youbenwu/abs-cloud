package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.MerchantCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantCustomerDao extends JpaRepository<MerchantCustomer,Long> {

    public MerchantCustomer findByMerchantIdAndUserId(Long merchantId, Long userId);


}
