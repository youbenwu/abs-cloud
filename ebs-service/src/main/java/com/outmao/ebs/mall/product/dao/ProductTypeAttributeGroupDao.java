package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductTypeAttributeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeAttributeGroupDao extends JpaRepository<ProductTypeAttributeGroup,Long> {

    public List<ProductTypeAttributeGroup> findAllByTypeId(Long typeId);

    public boolean existsByTypeIdAndKeyAndIdNot(Long typeId, String key, Long idNot);

}
