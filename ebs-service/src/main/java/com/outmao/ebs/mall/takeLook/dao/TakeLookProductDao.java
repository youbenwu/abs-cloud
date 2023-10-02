package com.outmao.ebs.mall.takeLook.dao;

import com.outmao.ebs.mall.takeLook.entity.TakeLookProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TakeLookProductDao extends JpaRepository<TakeLookProduct,Long> {


    public List<TakeLookProduct> findAllByLookId(Long takeLookId);


    public List<TakeLookProduct> findAllByLookIdIn(Collection<Long> lookIdIn);


    public void deleteAllByLookId(Long takeLookId);

}
