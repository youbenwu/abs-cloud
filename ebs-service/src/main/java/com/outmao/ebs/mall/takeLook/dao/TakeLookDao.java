package com.outmao.ebs.mall.takeLook.dao;

import com.outmao.ebs.mall.takeLook.entity.TakeLook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TakeLookDao extends JpaRepository<TakeLook,Long> {


    public long countByCustomerId(Long customerId);




}
