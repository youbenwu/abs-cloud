package com.outmao.ebs.mall.promotion.dao;

import com.outmao.ebs.mall.promotion.entity.GiftsProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface GiftsProductSkuDao extends JpaRepository<GiftsProductSku,Long> {

    public GiftsProductSku findByGiftsIdAndSkuId(Long giftsId, Long skuId);

    public void deleteAllByGiftsIdAndIdNotIn(Long giftsId, Collection<Long> idNotIn);

}
