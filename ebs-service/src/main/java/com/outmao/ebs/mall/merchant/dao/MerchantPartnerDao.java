package com.outmao.ebs.mall.merchant.dao;

import com.outmao.ebs.mall.merchant.entity.MerchantPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;
import java.util.List;

public interface MerchantPartnerDao extends JpaRepository<MerchantPartner,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select o from MerchantPartner o where o.id=?1")
    public MerchantPartner findByIdForUpdate(Long id);


    @Query("select e.id from MerchantPartner  e where e.parent.id=?1")
    public List<Long> findAllIdByParentId(Long parentId);


}
