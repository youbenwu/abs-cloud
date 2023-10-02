package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDao extends JpaRepository<Store,Long> {
}
