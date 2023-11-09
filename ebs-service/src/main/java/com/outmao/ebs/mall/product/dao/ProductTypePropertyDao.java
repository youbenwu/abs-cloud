package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductTypeProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductTypePropertyDao extends JpaRepository<ProductTypeProperty,Long> {

    public List<ProductTypeProperty> findAllByTypeId(Long typeId);

    public boolean existsByTypeIdAndKeyAndIdNot(Long typeId, String key, Long idNot);

    public void deleteAllByTypeId(Long typeId);


}
