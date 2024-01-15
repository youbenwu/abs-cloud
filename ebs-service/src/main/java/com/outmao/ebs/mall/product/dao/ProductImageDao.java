package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductImageDao extends JpaRepository<ProductImage,Long> {

    public List<ProductImage> findAllByProductId(Long productId);

    public void deleteAllByProductId(Long productId);
    public void deleteAllByProductIdIn(Collection<Long> productIdIn);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);

}
