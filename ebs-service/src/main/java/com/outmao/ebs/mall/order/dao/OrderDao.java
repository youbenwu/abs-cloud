package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import javax.persistence.LockModeType;
import java.util.Collection;

public interface OrderDao extends JpaRepository<Order,Long> {


    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select o from Order o where o.id=?1")
    public Order findByIdForUpdate(Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    public Order findByOrderNo(String orderNo);

    @Query("select sum(o.totalAmount) from Order o")
    public Double sumTotalAmount();


    public long countByCustomerId(Long customerId);


    public long countByPartnerIdAndStatusIn(Long partnerId, Integer[] statusIn);

    public long countByPartnerIdInAndStatusIn(Collection<Long> partnerIdIn, Integer[] statusIn);


}
