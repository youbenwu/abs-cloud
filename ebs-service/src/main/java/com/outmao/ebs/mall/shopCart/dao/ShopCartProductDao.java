package com.outmao.ebs.mall.shopCart.dao;

import com.outmao.ebs.mall.shopCart.entity.ShopCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ShopCartProductDao extends JpaRepository<ShopCartProduct,Long> {

    public List<ShopCartProduct> findAllByUserId(Long userId);

    public ShopCartProduct findByUserIdAndSkuId(Long userId, Long skuId);

    public void deleteAllByUserId(Long userId);

    public void deleteAllByUserIdAndSkuIdIn(Long userId, Collection<Long> skuIdIn);


}
