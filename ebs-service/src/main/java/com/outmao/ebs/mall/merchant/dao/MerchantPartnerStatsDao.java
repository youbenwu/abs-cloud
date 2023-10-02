package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.MerchantPartnerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MerchantPartnerStatsDao extends JpaRepository<MerchantPartnerStats,Long> {

    @Transactional
    @Modifying
    @Query("update MerchantPartnerStats e set e.customerCount=?2 where e.partner.id=?1")
    public void setCustomerCount(Long partnerId, long count);

    @Transactional
    @Modifying
    @Query("update MerchantPartnerStats e set e.childrenCustomerCount=?2 where e.partner.id=?1")
    public void setChildrenCustomerCount(Long partnerId, long count);


    @Transactional
    @Modifying
    @Query("update MerchantPartnerStats e set e.orderCount=?2 where e.partner.id=?1")
    public void setOrderCount(Long partnerId, long count);


    @Transactional
    @Modifying
    @Query("update MerchantPartnerStats e set e.childrenOrderCount=?2 where e.partner.id=?1")
    public void setChildrenOrderCount(Long partnerId, long count);


}
