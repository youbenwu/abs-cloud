package com.outmao.ebs.mall.distribution.dao;

import com.outmao.ebs.mall.distribution.entity.DistributionConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributionConfigDao extends JpaRepository<DistributionConfig,Long> {

    public DistributionConfig findByMerchantId(Long merchantId);

}
