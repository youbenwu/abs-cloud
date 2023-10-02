package com.outmao.ebs.mall.order.dao;

import com.outmao.ebs.mall.order.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressDao extends JpaRepository<OrderAddress,Long> {

    public void deleteAllByOrderId(Long orderId);

}
