package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductPropertyDao extends JpaRepository<ProductProperty,Long> {

    public List<ProductProperty> findAllByProductId(Long productId);

    public void deleteAllByProductId(Long productId);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);

}
