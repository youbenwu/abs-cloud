package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductMediaDao extends JpaRepository<ProductMedia,Long> {


    public List<ProductMedia> findAllByProductId(Long productId);

    public void deleteAllByProductId(Long productId);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);

}
