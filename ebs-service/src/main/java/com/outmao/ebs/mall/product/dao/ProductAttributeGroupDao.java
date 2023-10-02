package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductAttributeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductAttributeGroupDao extends JpaRepository<ProductAttributeGroup,Long> {


    public List<ProductAttributeGroup> findAllByProductId(Long productId);

    public void deleteAllByProductId(Long productId);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);

}
