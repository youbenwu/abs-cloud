package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductAttribute;
import com.outmao.ebs.mall.product.entity.ProductAttributeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductAttributeDao extends JpaRepository<ProductAttribute,Long> {

    public List<ProductAttribute> findAllByGroupId(Long groupId);


    public List<ProductAttribute> findAllByProductId(Long productId);


    public void deleteAllByProductId(Long productId);

    public void deleteAllByProductIdAndGroupIdNotIn(Long productId, Collection<Long> groupIdNotIn);

    public void deleteAllByGroupIdAndIdNotIn(Long groupId, Collection<Long> idNotIn);

    public void deleteAllByProductIdAndIdNotIn(Long productId, Collection<Long> idNotIn);

}
