package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.MerchantBroker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchantBrokerDao extends JpaRepository<MerchantBroker,Long> {

    public List<MerchantBroker> findAllByUserId(Long userId);

    @Query("select e.id from MerchantBroker e where e.merchant.id=?1")
    public List<Long> findAllIdByMerchantId(Long merchantId);


    @Query("select e.id from MerchantBroker e")
    public List<Long> findAllId();

}
