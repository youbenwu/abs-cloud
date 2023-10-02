package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.LockModeType;

public interface TicketOrderDao extends JpaRepository<TicketOrder,Long> , QuerydslPredicateExecutor<TicketOrder> {


    public TicketOrder findByOrderNo(String orderNo);


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from TicketOrder o where o.id=?1")
    public TicketOrder findByIdForUpdate(Long id);



}
