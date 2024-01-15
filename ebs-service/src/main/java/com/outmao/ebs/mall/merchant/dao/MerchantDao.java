package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.Merchant;
import com.outmao.ebs.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantDao extends JpaRepository<Merchant,Long> {

    public Merchant findByShopId(Long shopId);

    public Merchant findByOrgId(Long orgId);

    @Query("select m from Merchant m where m.user.id=?1")
    public Merchant findByUserId(Long userId);


    public boolean existsByUser(User user);


}
