package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantDao extends JpaRepository<Merchant,Long> {

    public Merchant findByShopId(Long shopId);

}
