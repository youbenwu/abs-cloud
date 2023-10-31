package com.outmao.ebs.portal.dao;

import com.outmao.ebs.portal.entity.AdvertOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface AdvertOrderDao extends JpaRepository<AdvertOrder,Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public AdvertOrder findByOrderNo(String orderNo);

}
