package com.outmao.ebs.mall.store.dao;

import com.outmao.ebs.mall.store.entity.StoreMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreMemberDao extends JpaRepository<StoreMember,Long> {

    public StoreMember findByStoreIdAndUserId(Long storeId, Long userId);


}
