package com.outmao.ebs.mall.product.dao;

import com.outmao.ebs.mall.product.entity.ProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductSnapshotDao extends JpaRepository<ProductSnapshot,Long> {


    public List<ProductSnapshot> findAllByIdIn(Collection<Long> idIn);


}
