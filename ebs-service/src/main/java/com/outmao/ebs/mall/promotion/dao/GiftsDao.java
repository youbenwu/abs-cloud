package com.outmao.ebs.mall.promotion.dao;

import com.outmao.ebs.mall.promotion.entity.Gifts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftsDao extends JpaRepository<Gifts,Long> {

    public Gifts findByProductId(Long productId);
}
