package com.outmao.ebs.mall.shop.dao;

import com.outmao.ebs.mall.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ShopDao extends JpaRepository<Shop,Long> {

    @Query("select s.id from Shop s where s.title=?1")
    public Long findIdByTitle(String title);

    @Query("select s.merchantId from Shop s where s.id=?1")
    public Long findMerchantIdById(Long id);

    public Shop findByUserId(Long userId);

    public List<Shop> findAllByIdIn(Collection<Long> idIn);

    public Shop findByOrgId(Long orgId);

}
