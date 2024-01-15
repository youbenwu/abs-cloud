package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductProperty;
import com.outmao.ebs.mall.product.entity.ProductPropertyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductPropertyItemDao extends JpaRepository<ProductPropertyItem,Long> {

    public List<ProductPropertyItem> findAllByPropertyId(Long propertyId);

    public void deleteAllByProductId(Long productId);
    public void deleteAllByProductIdIn(Collection<Long> productIdIn);

    public void deleteAllByProductIdAndPropertyIdNotIn(Long productId, Collection<Long> propertyIdNotIn);

    public void deleteAllByPropertyIdAndIdNotIn(Long propertyId, Collection<Long> idNotIn);

}
