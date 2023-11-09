package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductTypeAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeAttributeDao extends JpaRepository<ProductTypeAttribute,Long> {


    public List<ProductTypeAttribute> findAllByGroupId(Long groupId);

    public boolean existsByTypeIdAndKeyAndIdNot(Long typeId, String key, Long idNot);

    public void deleteAllByTypeId(Long typeId);

}
